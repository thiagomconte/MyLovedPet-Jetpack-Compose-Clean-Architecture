package com.conte.mylovedpet.presentation.petevent.viewmodel

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.conte.domain.module.event.model.PetEvent
import com.conte.mylovedpet.utils.Updatable

@Stable
interface PetEventUiState {
    val events: List<PetEvent>
}

class MutablePetEventUiState : PetEventUiState, Updatable {
    override var events: List<PetEvent> by mutableStateOf(emptyList())
}