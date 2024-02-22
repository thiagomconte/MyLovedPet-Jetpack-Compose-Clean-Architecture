package com.conte.mylovedpet.presentation.addpetevent.viewmodel

import java.util.Calendar

sealed interface AddPetEventUiEvent {
    object OnBack : AddPetEventUiEvent
    data class OnAddPetEvent(val date: Calendar, val notificationTitle: String, val notificationDescription: String, val allowNotification: Boolean, val notificationId: Int) :
        AddPetEventUiEvent
}