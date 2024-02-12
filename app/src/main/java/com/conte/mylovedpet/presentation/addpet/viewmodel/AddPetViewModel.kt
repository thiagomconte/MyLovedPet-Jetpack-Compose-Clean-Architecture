package com.conte.mylovedpet.presentation.addpet.viewmodel

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.conte.domain.module.commons.logError
import com.conte.domain.module.pet.model.Pet
import com.conte.domain.module.pet.model.PetGender
import com.conte.domain.module.pet.model.PetType
import com.conte.domain.module.pet.usecase.InsertPetUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class AddPetViewModel @Inject constructor(
    private val insertPetUseCase: InsertPetUseCase
) : ViewModel(), AddPetUiAction {

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
        _uiState.validBirthday = isDateOfBirthValid(value)
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

    override fun onSubmit() {
        viewModelScope.launch(Dispatchers.IO) {
            val pet = Pet(
                name = uiState.name,
                birthday = uiState.birthday,
                breed = uiState.breed,
                type = uiState.petType,
                gender = uiState.petGender,
                uri = uiState.uri
            )
            insertPetUseCase(pet).onSuccess {
                _channel.send(AddPetUiEvent.OnSubmit)
            }.onFailure {
                logError { it.message }
            }
        }
    }

    override fun onSelectImage() {
        viewModelScope.launch(Dispatchers.Default) {
            _channel.send(AddPetUiEvent.OnSelectImage)
        }
    }

    fun onImageSelected(uri: Uri) {
        _uiState.uri = uri
    }

    private fun isDateOfBirthValid(date: String): Boolean {
        try {
            val formatter = DateTimeFormatter.ofPattern("ddMMyyyy")
            val localDate = LocalDate.parse(date, formatter)
            if (!formatter.format(localDate).equals(date) || localDate > LocalDate.now()) {
                return false
            }
            return true
        } catch (e: Exception) {
            return false
        }
    }
}