package com.conte.data.module.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.conte.data.module.database.entity.PetEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PetDao : BaseDao<PetEntity> {

    @Query("SELECT * from pets")
    suspend fun getAll(): List<PetEntity>

    @Query("SELECT * from pets")
    fun flowAll(): Flow<List<PetEntity>>

    @Query("SELECT * from pets WHERE id = :id")
    suspend fun getById(id: Int): PetEntity
}