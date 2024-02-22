package com.conte.mylovedpet.presentation.addpetevent.viewmodel

sealed interface AddPetEventUiEvent {
    object OnBack : AddPetEventUiEvent
    data class OnAddPetEvent(val allowNotification: Boolean) : AddPetEventUiEvent
}