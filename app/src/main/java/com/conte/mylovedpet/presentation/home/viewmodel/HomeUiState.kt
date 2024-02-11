package com.conte.mylovedpet.presentation.home.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.conte.domain.module.pet.model.Pet
import com.conte.mylovedpet.utils.Updatable

interface HomeUiState {
    val pets: List<Pet>
}

class MutableHomeUiState : HomeUiState, Updatable {

    override var pets: List<Pet> by mutableStateOf(emptyList())
}

