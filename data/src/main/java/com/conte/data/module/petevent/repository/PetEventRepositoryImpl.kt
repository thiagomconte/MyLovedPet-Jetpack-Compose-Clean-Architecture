package com.conte.data.module.petevent.repository

import com.conte.data.module.pet.mapper.toPetWithEvents
import com.conte.domain.module.event.repository.PetEventRepository
import com.conte.domain.module.pet.model.PetWithEvents
import javax.inject.Inject

class PetEventRepositoryImpl @Inject constructor(
    private val localDataSource: PetEventLocalDataSource
) : PetEventRepository {

    override suspend fun getAllPetEventsByPet(petId: Int): Result<PetWithEvents> =
        runCatching {
            localDataSource.getAllEventsByPet(petId).getOrThrow().toPetWithEvents()
        }
}