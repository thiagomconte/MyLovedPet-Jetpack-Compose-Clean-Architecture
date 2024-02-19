package com.conte.data.module.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.conte.data.module.database.entity.PetEntity
import com.conte.data.module.database.entity.PetWithEventsEntity

@Dao
interface PetEventDao : BaseDao<PetEntity> {

    @Query("SELECT * FROM pets WHERE id = :petId")
    suspend fun getAllByPet(petId: Int): PetWithEventsEntity
}