package com.conte.data.module.database.pet.mapper

import com.conte.data.module.database.entity.PetEntity
import com.conte.domain.module.pet.model.Pet

fun Pet.toEntity() = PetEntity(
    id, name, birthday, breed, type, gender
)

fun PetEntity.toDomain() = Pet(id, name, birthday, breed, type, gender)

fun List<PetEntity>.toDomain() = map { it.toDomain() }