package com.conte.mylovedpet.presentation.addpet.viewmodel

sealed interface AddPetUiEvent {
    object OnBack: AddPetUiEvent
    object OnSubmit: AddPetUiEvent
    object OnSelectImage: AddPetUiEvent
}