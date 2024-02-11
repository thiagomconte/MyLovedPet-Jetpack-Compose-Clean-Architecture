package com.conte.mylovedpet.presentation.addpet.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.conte.domain.module.pet.model.PetGender
import com.conte.domain.module.pet.model.PetType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class AddPetViewModel : ViewModel(), AddPetUiAction {

    private val _channel = Channel<AddPetUiEvent>()
    val channel = _channel.receiveAsFlow()

    private val _uiState = MutableAddPetUiState()
    val uiState = _uiState

    override fun onBack() {
        viewModelScope.launch(Dispatchers.Default) {
            _channel.send(AddPetUiEvent.OnBack)
        }
    }

    override fun onNameTyping(value: String) {
        _uiState.name = value
    }

    override fun onBirthdayTyping(value: String) {
        _uiState.birthday = value
    }

    override fun onBreedTyping(value: String) {
        _uiState.breed = value
    }

    override fun onAvatarClick(type: PetType) {
        _uiState.petType = type
    }

    override fun onPetGenderClick(gender: PetGender) {
        _uiState.petGender = gender
    }
}