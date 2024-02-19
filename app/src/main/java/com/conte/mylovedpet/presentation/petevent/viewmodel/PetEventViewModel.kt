package com.conte.mylovedpet.presentation.petevent.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.conte.domain.module.event.usecase.GetAllPetEventsByPetUseCase
import com.conte.mylovedpet.navigation.Navigation
import com.conte.mylovedpet.utils.toIntOrInvalidInt
import com.conte.mylovedpet.utils.update
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PetEventViewModel @Inject constructor(
    private val getAllPetEventsByPetUseCase: GetAllPetEventsByPetUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel(), PetEventUiAction {

    private val petId = savedStateHandle.get<String>(Navigation.AddEvent.param).toIntOrInvalidInt()

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

    private fun loadEvents() {
        viewModelScope.launch(Dispatchers.IO) {
            getAllPetEventsByPetUseCase(petId)
                .onSuccess {
                    _uiState.update {
                        events = it.events
                    }
                }.onFailure {

                }
        }
    }
}