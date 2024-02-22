package com.conte.mylovedpet.presentation.addpetevent.viewmodel

import androidx.compose.runtime.Stable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.conte.mylovedpet.utils.Updatable

@Stable
interface AddPetEventUiState {
    val eventName: String
    val petName: String
    val eventDate: String
    val eventTime: String
    val validEventDate: Boolean
    val validEventTime: Boolean
    val enableSaveButton: Boolean
    val allowNotification: Boolean
}

class MutableAddPetEventUiState(petName: String) : AddPetEventUiState, Updatable {
    override var eventName: String by mutableStateOf("")
    override var petName: String by mutableStateOf(petName)
    override var eventDate: String by mutableStateOf("")
    override var eventTime: String by mutableStateOf("")
    override var validEventDate: Boolean by mutableStateOf(true)
    override var validEventTime: Boolean by mutableStateOf(true)
    override val enableSaveButton: Boolean by derivedStateOf {
        eventName.isNotEmpty() && eventDate.isNotEmpty() && validEventDate && eventTime.isNotEmpty() && validEventTime
    }
    override var allowNotification: Boolean by mutableStateOf(true)
}