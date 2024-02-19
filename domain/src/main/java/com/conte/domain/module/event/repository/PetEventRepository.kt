package com.conte.domain.module.event.repository

import com.conte.domain.module.pet.model.PetWithEvents

interface PetEventRepository {

    suspend fun getAllPetEventsByPet(petId: Int): Result<PetWithEvents>
}