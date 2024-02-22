package com.conte.mylovedpet.presentation.addpetevent.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.conte.domain.module.commons.logError
import com.conte.domain.module.petevent.model.PetEvent
import com.conte.domain.module.petevent.usecase.InsertPetEventUseCase
import com.conte.domain.module.petevent.usecase.UpdatePetEventWorkerIdUseCase
import com.conte.mylovedpet.PetEventWorker
import com.conte.mylovedpet.R
import com.conte.mylovedpet.navigation.Navigation
import com.conte.mylovedpet.utils.StringResourcesProvider
import com.conte.mylovedpet.utils.orInvalidInt
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class AddPetEventViewModel @Inject constructor(
    private val insertPetEventUseCase: InsertPetEventUseCase,
    private val updatePetEventWorkerIdUseCase: UpdatePetEventWorkerIdUseCase,
    savedStateHandle: SavedStateHandle,
    private val workManager: WorkManager,
    private val stringResourcesProvider: StringResourcesProvider
) : ViewModel(), AddPetEventUiAction {

    private val petId =
        savedStateHandle.get<Int>(Navigation.AddPetEvent.petId).orInvalidInt()
    private val petName = savedStateHandle.get<String>(Navigation.AddPetEvent.petName).orEmpty()

    private val _uiState = MutableAddPetEventUiState(petName = petName)
    val uiState: AddPetEventUiState = _uiState

    private val _channel = Channel<AddPetEventUiEvent>()
    val channel = _channel.receiveAsFlow()

    private var workerId: UUID? = null
    private var petEventIdInserted: Long = -1L

    override fun onBack() {
        viewModelScope.launch(Dispatchers.Default) {
            _channel.send(AddPetEventUiEvent.OnBack)
        }
    }

    override fun onEventNameTyping(value: String) {
        _uiState.eventName = value
    }

    override fun onEventDateTyping(value: String) {
        if (value.length < 9) {
            _uiState.eventDate = value
            _uiState.validEventDate = isDateOfEventValid(value)
        }
    }

    override fun onEventTimeTyping(value: String) {
        if (value.length < 5) {
            _uiState.eventTime = value
            _uiState.validEventTime = isTimeValid(value)
        }
    }

    override fun onAllowNotificationClick(value: Boolean) {
        _uiState.allowNotification = value
    }

    override fun onSubmit() {
        viewModelScope.launch(Dispatchers.Default) {
            val date = uiState.eventDate.plus(uiState.eventTime)
            val petEvent = PetEvent(
                name = uiState.eventName,
                time = date,
                petId = petId,
                workerId = workerId
            )
            insertPetEventUseCase(petEvent).onSuccess {
                petEventIdInserted = it
                onSubmitted()
            }.onFailure {

            }
        }
    }

    private fun updatePetEventWorkerRequestId(uuid: UUID) {
        viewModelScope.launch(Dispatchers.Default) {
            updatePetEventWorkerIdUseCase(petEventIdInserted, uuid)
        }
    }

    fun scheduleWorker(onFinish: () -> Unit) {
        try {
            val date = calculateNotificationDate(uiState.eventDate.plus(uiState.eventTime))
            val notificationTitle = stringResourcesProvider.getString(resId = R.string.add_pet_event_notification_title, uiState.eventName)
            val notificationDescription =
                stringResourcesProvider.getString(resId = R.string.add_pet_event_notification_description, uiState.eventName, uiState.petName)
            val inputData = Data.Builder()
                .putString(PetEventWorker.KEY_NOTIFICATION_NAME, notificationTitle)
                .putString(PetEventWorker.KEY_NOTIFICATION_DESCRIPTION, notificationDescription)
                .build()
            val workRequest = OneTimeWorkRequestBuilder<PetEventWorker>()
                .setInputData(inputData)
                .setInitialDelay(date.timeInMillis - System.currentTimeMillis(), java.util.concurrent.TimeUnit.MILLISECONDS)
                .build()
            updatePetEventWorkerRequestId(workRequest.id)
            workManager.enqueue(workRequest)
            onFinish()
        } catch (e: Exception) {
            logError { "Failure to schedule notification worker. $e" }
            onFinish()
        }
    }

    fun onPermissionGranted() {
        scheduleWorker {
            viewModelScope.launch(Dispatchers.Default) {
                _channel.send(AddPetEventUiEvent.OnBack)
            }
        }
    }

    private suspend fun onSubmitted() {
        _channel.send(AddPetEventUiEvent.OnAddPetEvent(allowNotification = _uiState.allowNotification))
    }

    private fun calculateNotificationDate(dateString: String): Calendar {
        val dateFormat = SimpleDateFormat("ddMMyyyyHHmm", Locale.getDefault())
        val date = dateFormat.parse(dateString)

        val targetDate = Calendar.getInstance()
        date?.let { targetDate.time = it }

        targetDate.add(Calendar.DAY_OF_MONTH, -1)
        return targetDate
    }

    private fun isDateOfEventValid(date: String): Boolean {
        try {
            val formatter = DateTimeFormatter.ofPattern("ddMMyyyy")
            val localDate = LocalDate.parse(date, formatter)
            if (!formatter.format(localDate).equals(date) || localDate < LocalDate.now()) {
                return false
            }
            return true
        } catch (e: Exception) {
            return false
        }
    }

    private fun isTimeValid(time: String): Boolean {
        try {
            val formatter = DateTimeFormatter.ofPattern("HHmm")
            val localTime = LocalTime.parse(time, formatter)
            if (!formatter.format(localTime).equals(time)) {
                return false
            }
            return true
        } catch (e: Exception) {
            return false
        }
    }
}