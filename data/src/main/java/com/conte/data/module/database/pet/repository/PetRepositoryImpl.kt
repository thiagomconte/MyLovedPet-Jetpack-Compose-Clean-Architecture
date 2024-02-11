package com.conte.data.module.database.pet.repository

import com.conte.data.module.database.pet.mapper.toDomain
import com.conte.data.module.database.pet.mapper.toEntity
import com.conte.domain.module.pet.model.Pet
import com.conte.domain.module.pet.repository.PetRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PetRepositoryImpl @Inject constructor(
    private val localDataSource: PetLocalDataSource
) : PetRepository {
    override suspend fun insert(pet: Pet): Result<Unit> = runCatching {
        localDataSource.insert(pet.toEntity())
    }

    override suspend fun getAll(): Result<List<Pet>> = runCatching {
        localDataSource.getAll().getOrThrow().toDomain()
    }

    override fun flowAll(): Flow<List<Pet>> = localDataSource.flowAll().map { it.toDomain() }
}