package com.conte.data.module.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.conte.data.module.database.entity.PetEventEntity
import com.conte.data.module.database.entity.PetWithEventsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PetEventDao : BaseDao<PetEventEntity> {

    @Query("SELECT * FROM pets WHERE id = :petId")
    suspend fun getAllByPet(petId: Int): PetWithEventsEntity

    @Query("SELECT * FROM pets WHERE id = :petId")
    fun flowAllByPet(petId: Int): Flow<PetWithEventsEntity>
}