package com.conte.mylovedpet.presentation.home.viewmodel

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.conte.domain.module.pet.model.Pet
import com.conte.mylovedpet.utils.Updatable

@Stable
interface HomeUiState {
    val pets: List<Pet>
    val error: Boolean
}

class MutableHomeUiState : HomeUiState, Updatable {

    override var pets: List<Pet> by mutableStateOf(emptyList())
    override var error: Boolean by mutableStateOf(false)

    companion object {
        fun buildFake() = MutableHomeUiState().apply {
            pets = listOf(Pet.buildFake())
        }
    }
}

