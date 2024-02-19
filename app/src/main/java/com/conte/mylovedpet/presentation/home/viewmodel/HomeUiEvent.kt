package com.conte.mylovedpet.presentation.home.viewmodel

sealed interface HomeUiEvent {
    object OnAddPet : HomeUiEvent
    object OnSettings : HomeUiEvent
    data class OnPetClick(val id: Int) : HomeUiEvent
}