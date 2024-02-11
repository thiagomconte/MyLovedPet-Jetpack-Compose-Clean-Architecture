package com.conte.domain.module.pet.model

data class Pet(
    val id: Int,
    val name: String,
    val birthday: String,
    val breed: String,
    val type: PetType,
    val gender: PetGender,
) {

    companion object {
        fun buildFake() = Pet(
            id = 1,
            name = "Thor",
            birthday = "qualquer",
            breed = "Doberman",
            type = PetType.DOG,
            gender = PetGender.MALE
        )
    }
}
