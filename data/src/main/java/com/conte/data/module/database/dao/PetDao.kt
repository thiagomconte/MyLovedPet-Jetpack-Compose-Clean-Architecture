package com.conte.data.module.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.conte.data.module.database.entity.PetEntity

@Dao
interface PetDao : BaseDao<PetEntity> {

    @Query("SELECT * from pets")
    suspend fun getAll(): List<PetEntity>

    @Query("SELECT * from pets WHERE id = :id")
    suspend fun get(id: Int): PetEntity
}