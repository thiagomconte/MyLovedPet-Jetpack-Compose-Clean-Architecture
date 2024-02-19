package com.conte.data.module.pet.repository

import com.conte.data.module.database.dao.PetDao
import com.conte.data.module.database.entity.PetEntity
import com.conte.domain.module.commons.logError
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
    }.onFailure {
        logError { "Failure to getAll on PetLocalDataSource. $it" }
    }

    fun flowAll(): Flow<List<PetEntity>> = petDao.flowAll()

    suspend fun getById(id: Int): Result<PetEntity> = runCatching {
        petDao.get(id)
    }.onFailure {
        logError { "Failure to getById($id) on PetLocalDataSource. $it" }
    }
}