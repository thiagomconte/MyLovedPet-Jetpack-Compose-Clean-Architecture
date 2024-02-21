package com.conte.domain.module.petevent.repository

import com.conte.domain.module.pet.model.PetWithEvents
import com.conte.domain.module.petevent.model.PetEvent
import kotlinx.coroutines.flow.Flow

interface PetEventRepository {

    suspend fun getAllPetEventsByPet(petId: Int): Result<PetWithEvents>

    suspend fun insertPetEvent(petEvent: PetEvent): Result<Long>

    suspend fun flowAllPetEventsByPet(petId: Int): Flow<PetWithEvents>

    suspend fun updatePetEventNotificationId(petEventId: Long, notificationId: Int): Result<Unit>

    suspend fun getPetEventById(id: Int): Result<PetEvent>
}