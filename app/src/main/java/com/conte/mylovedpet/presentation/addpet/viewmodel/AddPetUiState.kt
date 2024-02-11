package com.conte.mylovedpet.presentation.addpet.viewmodel

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.conte.domain.module.pet.model.PetGender
import com.conte.domain.module.pet.model.PetType
import com.conte.mylovedpet.utils.Updatable

@Stable
interface AddPetUiState {
    val name: String
    val birthday: String
    val breed: String
    val petType: PetType
    val petGender: PetGender
}

class MutableAddPetUiState : AddPetUiState, Updatable {
    override var name: String by mutableStateOf("")
    override var birthday: String by mutableStateOf("")
    override var breed: String by mutableStateOf("")
    override var petType: PetType by mutableStateOf(PetType.DOG)
    override var petGender: PetGender by mutableStateOf(PetGender.MALE)
}