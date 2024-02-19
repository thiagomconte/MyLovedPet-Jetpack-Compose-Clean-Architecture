package com.conte.domain.module.event.usecase

import com.conte.domain.module.event.repository.PetEventRepository
import com.conte.domain.module.pet.model.PetWithEvents
import javax.inject.Inject

class GetAllPetEventsByPetUseCase @Inject constructor(
    private val repository: PetEventRepository
) {

    suspend operator fun invoke(petId: Int): Result<PetWithEvents> =
        repository.getAllPetEventsByPet(petId)
}