package com.conte.data.module.database.pet.mapper

import android.net.Uri
import com.conte.data.module.database.entity.PetEntity
import com.conte.domain.module.pet.model.Pet

fun Pet.toEntity() = PetEntity(
    id, name, birthday, breed, type, gender, uri?.toString()
)

fun PetEntity.toDomain(): Pet {
    val uri = try {
        Uri.parse(this.image)
    } catch (_: Exception) {
        null
    }
    return Pet(id, name, birthday, breed, type, gender, uri)
}

fun List<PetEntity>.toDomain() = map { it.toDomain() }