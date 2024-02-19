package com.conte.mylovedpet.presentation.petevent.viewmodel

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.conte.domain.module.event.model.PetEvent
import com.conte.domain.module.pet.model.Pet
import com.conte.mylovedpet.utils.Updatable

@Stable
interface PetEventUiState {
    val pet: Pet?
    val events: List<PetEvent>
}

class MutablePetEventUiState : PetEventUiState, Updatable {
    override var pet: Pet? by mutableStateOf(null)
    override var events: List<PetEvent> by mutableStateOf(emptyList())
}