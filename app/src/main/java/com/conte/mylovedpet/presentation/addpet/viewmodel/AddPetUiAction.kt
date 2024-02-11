package com.conte.mylovedpet.presentation.addpet.viewmodel

import com.conte.domain.module.pet.model.PetGender
import com.conte.domain.module.pet.model.PetType

interface AddPetUiAction {
    fun onBack()
    fun onNameTyping(value: String)
    fun onBirthdayTyping(value: String)
    fun onBreedTyping(value: String)
    fun onAvatarClick(type: PetType)
    fun onPetGenderClick(gender: PetGender)
    fun submit()

    companion object {
        fun buildFake() = object : AddPetUiAction {
            override fun onBack() {}
            override fun onNameTyping(value: String) {}
            override fun onBirthdayTyping(value: String) {}
            override fun onBreedTyping(value: String) {}
            override fun onAvatarClick(type: PetType) {}
            override fun onPetGenderClick(gender: PetGender) {}
            override fun submit() {}
        }
    }
}