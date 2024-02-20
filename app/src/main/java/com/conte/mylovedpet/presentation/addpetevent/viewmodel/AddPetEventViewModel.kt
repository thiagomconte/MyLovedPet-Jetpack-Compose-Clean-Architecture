package com.conte.mylovedpet.presentation.addpetevent.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.conte.domain.module.petevent.model.PetEvent
import com.conte.domain.module.petevent.usecase.InsertPetEventUseCase
import com.conte.mylovedpet.navigation.Navigation
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
import javax.inject.Inject

@HiltViewModel
class AddPetEventViewModel @Inject constructor(
    private val insertPetEventUseCase: InsertPetEventUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel(), AddPetEventUiAction {

    private val petId =
        savedStateHandle.get<Int>(Navigation.AddPetEvent.petId).orInvalidInt()
    private val petName = savedStateHandle.get<String>(Navigation.AddPetEvent.petName).orEmpty()

    private val _uiState = MutableAddPetEventUiState(petName = petName)
    val uiState: AddPetEventUiState = _uiState

    private val _channel = Channel<AddPetEventUiEvent>()
    val channel = _channel.receiveAsFlow()

    override fun onBack() {
        viewModelScope.launch(Dispatchers.Default) {
            _channel.send(AddPetEventUiEvent.OnBack)
        }
    }

    override fun onEventNameTyping(value: String) {
        _uiState.eventName = value
    }

    override fun onEventDateTyping(value: String) {
        _uiState.eventDate = value
        _uiState.validEventDate = isDateOfEventValid(value)
    }

    override fun onEventTimeTyping(value: String) {
        _uiState.eventTime = value
        _uiState.validEventTime = isTimeValid(value)
    }

    override fun onSubmit() {
        viewModelScope.launch(Dispatchers.Default) {
            val date = uiState.eventDate.plus(uiState.eventTime)
            val petEvent = PetEvent(
                name = uiState.eventName,
                time = date,
                petId = petId
            )
            insertPetEventUseCase(petEvent).onSuccess {
                _channel.send(AddPetEventUiEvent.OnAddPetEvent(calculateNotificationDate(date)))
            }.onFailure {

            }
        }
    }

    private fun calculateNotificationDate(dateString: String): Calendar {
        val dateFormat = SimpleDateFormat("ddMMyyyyHHmm", Locale.getDefault())
        val date = dateFormat.parse(dateString)

        val targetDate = Calendar.getInstance()
        date?.let { targetDate.time = it }

        // Subtrai um dia da data desejada
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