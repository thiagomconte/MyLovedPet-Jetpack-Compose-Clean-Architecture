package com.conte.data.module.database.entity

import androidx.room.Embedded
import androidx.room.Relation

data class PetWithEventsEntity(
    @Embedded
    val pet: PetEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "pet_id"
    )
    val events: List<PetEventEntity>
)
