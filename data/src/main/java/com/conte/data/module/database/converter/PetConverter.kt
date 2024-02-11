package com.conte.data.module.database.converter

import androidx.room.TypeConverter
import com.conte.domain.module.pet.model.PetGender
import com.conte.domain.module.pet.model.PetType

class PetConverter {

    @TypeConverter
    fun fromPetType(value: PetType): String {
        return value.name
    }

    @TypeConverter
    fun toPetType(value: String): PetType {
        return enumValueOf(value)
    }

    @TypeConverter
    fun fromPetGender(value: PetGender): String {
        return value.name
    }

    @TypeConverter
    fun toPetGender(value: String): PetGender {
        return enumValueOf(value)
    }
}