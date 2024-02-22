package com.conte.mylovedpet.presentation.petevent.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.WorkManager
import com.conte.domain.module.petevent.model.PetEvent
import com.conte.domain.module.petevent.usecase.DeletePetEventUseCase
import com.conte.domain.module.petevent.usecase.FlowAllPetEventsByPetUseCase
import com.conte.mylovedpet.navigation.Navigation
import com.conte.mylovedpet.utils.toIntOrInvalidInt
import com.conte.mylovedpet.utils.update
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PetEventViewModel @Inject constructor(
    private val flowAllPetEventsByPetUseCase: FlowAllPetEventsByPetUseCase,
    private val deletePetEventUseCase: DeletePetEventUseCase,
    private val workManager: WorkManager,
    savedStateHandle: SavedStateHandle
) : ViewModel(), PetEventUiAction {

    private val petId = savedStateHandle.get<String>(Navigation.PetEvent.petId).toIntOrInvalidInt()

    private val _channel = Channel<PetEventUiEvent>()
    val channel = _channel.receiveAsFlow()

    private val _uiState = MutablePetEventUiState()
    val uiState: PetEventUiState = _uiState

    init {
        loadEvents()
    }

    override fun onBack() {
        viewModelScope.launch(Dispatchers.Default) {
            _channel.send(PetEventUiEvent.OnBack)
        }
    }

    override fun onAddEventClick() {
        viewModelScope.launch(Dispatchers.Default) {
            uiState.pet?.let {
                _channel.send(PetEventUiEvent.OnAddEventClick(it.id, it.name))
            }
        }
    }

    override fun onDeleteClick(petEvent: PetEvent) {
        viewModelScope.launch(Dispatchers.IO) {
            deletePetEventUseCase(petEvent).onSuccess {
                petEvent.workerId?.let {
                    workManager.cancelWorkById(it)
                }
            }.onFailure {

            }
        }
    }

    private fun loadEvents() {
        viewModelScope.launch(Dispatchers.IO) {
            flowAllPetEventsByPetUseCase(petId)
                .onEach {
                    _uiState.update {
                        pet = it.pet
                        events = it.events
                    }
                }
                .launchIn(viewModelScope)
        }
    }
}