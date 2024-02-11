package com.conte.mylovedpet.presentation.home.viewmodel

sealed interface HomeUiEvent {
    object OnAddPet : HomeUiEvent
    object OnSettings : HomeUiEvent
}