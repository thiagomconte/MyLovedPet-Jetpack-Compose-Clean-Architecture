package com.conte.domain.module.pet.model

import android.net.Uri

data class Pet(
    val id: Int = 0,
    val name: String,
    val birthday: String,
    val breed: String,
    val type: PetType,
    val gender: PetGender,
    val uri: Uri?
) {

    companion object {
        fun buildFake() = Pet(
            id = 1,
            name = "Thor",
            birthday = "qualquer",
            breed = "Doberman",
            type = PetType.DOG,
            gender = PetGender.MALE,
            uri = null
        )
    }
}
