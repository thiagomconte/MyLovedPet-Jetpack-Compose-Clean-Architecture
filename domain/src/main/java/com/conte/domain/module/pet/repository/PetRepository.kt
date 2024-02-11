package com.conte.domain.module.pet.repository

import com.conte.domain.module.pet.model.Pet

interface PetRepository {

    suspend fun insert(pet: Pet): Result<Unit>

    suspend fun getAll(): Result<List<Pet>>
}