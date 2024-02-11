package com.conte.data.module.database.pet.repository

import com.conte.data.module.database.dao.PetDao
import com.conte.data.module.database.entity.PetEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PetLocalDataSource @Inject constructor(
    private val petDao: PetDao
) {

    suspend fun insert(pet: PetEntity): Result<Unit> = runCatching {
        petDao.insert(pet)
    }

    suspend fun getAll(): Result<List<PetEntity>> = runCatching {
        petDao.getAll()
    }

    fun flowAll(): Flow<List<PetEntity>> = petDao.flowAll()
}