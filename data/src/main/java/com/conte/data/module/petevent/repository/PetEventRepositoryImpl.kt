package com.conte.data.module.petevent.repository

import com.conte.data.module.pet.mapper.toPetWithEvents
import com.conte.data.module.petevent.mapper.toEntity
import com.conte.domain.module.pet.model.PetWithEvents
import com.conte.domain.module.petevent.model.PetEvent
import com.conte.domain.module.petevent.repository.PetEventRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PetEventRepositoryImpl @Inject constructor(
    private val localDataSource: PetEventLocalDataSource
) : PetEventRepository {

    override suspend fun getAllPetEventsByPet(petId: Int): Result<PetWithEvents> =
        runCatching {
            localDataSource.getAllEventsByPet(petId).getOrThrow().toPetWithEvents()
        }

    override suspend fun insertPetEvent(petEvent: PetEvent): Result<Unit> = runCatching {
        localDataSource.insertPetEvent(petEvent.toEntity()).getOrThrow()
    }

    override suspend fun flowAllPetEventsByPet(petId: Int): Flow<PetWithEvents> =
        localDataSource.flowAllEventsByPet(petId).map { it.toPetWithEvents() }
}