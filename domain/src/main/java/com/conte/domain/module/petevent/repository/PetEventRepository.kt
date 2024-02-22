package com.conte.domain.module.petevent.repository

import com.conte.domain.module.pet.model.PetWithEvents
import com.conte.domain.module.petevent.model.PetEvent
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface PetEventRepository {

    suspend fun getAllPetEventsByPet(petId: Int): Result<PetWithEvents>

    suspend fun insertPetEvent(petEvent: PetEvent): Result<Long>

    suspend fun flowAllPetEventsByPet(petId: Int): Flow<PetWithEvents>

    suspend fun updatePetEventWorkerId(petEventId: Long, workerId: UUID): Result<Unit>

    suspend fun getPetEventById(id: Int): Result<PetEvent>

    suspend fun deletePetEvent(petEvent: PetEvent): Result<Unit>
}