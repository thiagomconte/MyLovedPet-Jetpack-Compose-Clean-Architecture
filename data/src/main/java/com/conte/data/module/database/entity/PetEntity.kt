package com.conte.data.module.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.conte.domain.module.pet.model.PetGender
import com.conte.domain.module.pet.model.PetType

@Entity(tableName = "pets")
data class PetEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "birthday")
    val birthday: String,
    @ColumnInfo(name = "breed")
    val breed: String,
    @ColumnInfo(name = "pet_type")
    val type: PetType,
    @ColumnInfo(name = "pet_gender")
    val gender: PetGender,
    @ColumnInfo(name = "pet_image")
    val image: String?
)
