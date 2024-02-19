package com.conte.mylovedpet.presentation.addpetevent.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.conte.mylovedpet.navigation.Navigation
import com.conte.mylovedpet.utils.orInvalidInt
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class AddPetEventViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel(), AddPetEventUiAction {

    private val petId =
        savedStateHandle.get<Int>(Navigation.AddPetEvent.petId).orInvalidInt()
    private val petName = savedStateHandle.get<String>(Navigation.AddPetEvent.petName).orEmpty()

    private val _uiState = MutableAddPetEventUiState(petName = petName)
    val uiState: AddPetEventUiState = _uiState

    private val _channel = Channel<AddPetEventUiEvent>()
    val channel = _channel.receiveAsFlow()

    override fun onBack() {
        viewModelScope.launch(Dispatchers.Default) {
            _channel.send(AddPetEventUiEvent.OnBack)
        }
    }

    override fun onEventNameTyping(value: String) {
        _uiState.eventName = value
    }

    override fun onEventDateTyping(value: String) {
        _uiState.eventDate = value
        _uiState.validEventDate = isDateOfEventValid(value)
    }

    private fun isDateOfEventValid(date: String): Boolean {
        try {
            val formatter = DateTimeFormatter.ofPattern("ddMMyyyy")
            val localDate = LocalDate.parse(date, formatter)
            if (!formatter.format(localDate).equals(date) || localDate < LocalDate.now()) {
                return false
            }
            return true
        } catch (e: Exception) {
            return false
        }
    }
}