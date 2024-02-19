package com.conte.mylovedpet.presentation.petevent.viewmodel

sealed interface PetEventUiEvent {
    object OnBack : PetEventUiEvent
    data class OnAddEventClick(val petId: Int, val petName: String) : PetEventUiEvent
}