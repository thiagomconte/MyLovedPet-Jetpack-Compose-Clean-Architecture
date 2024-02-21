package com.conte.domain.module.pet.repository

import com.conte.domain.module.pet.model.Pet
import kotlinx.coroutines.flow.Flow

interface PetRepository {

    suspend fun insert(pet: Pet): Result<Unit>

    suspend fun getAll(): Result<List<Pet>>

    fun flowAll(): Flow<List<Pet>>

    suspend fun getById(id: Int): Result<Pet>

    suspend fun update(pet: Pet): Result<Unit>

}