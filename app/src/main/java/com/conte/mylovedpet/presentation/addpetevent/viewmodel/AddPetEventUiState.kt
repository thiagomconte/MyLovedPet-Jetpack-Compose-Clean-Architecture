package com.conte.mylovedpet.presentation.addpetevent.viewmodel

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.conte.mylovedpet.utils.Updatable

@Stable
interface AddPetEventUiState {
    val eventName: String
    val petName: String
    val eventDate: String
    val validEventDate: Boolean
}

class MutableAddPetEventUiState(petName: String) : AddPetEventUiState, Updatable {
    override var eventName: String by mutableStateOf("")
    override var petName: String by mutableStateOf(petName)
    override var eventDate: String by mutableStateOf("")
    override var validEventDate: Boolean by mutableStateOf(true)
}