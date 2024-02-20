package com.conte.domain.module.petevent.usecase

import com.conte.domain.module.pet.model.PetWithEvents
import com.conte.domain.module.petevent.repository.PetEventRepository
import javax.inject.Inject

class GetAllPetEventsByPetUseCase @Inject constructor(
    private val repository: PetEventRepository
) {

    suspend operator fun invoke(petId: Int): Result<PetWithEvents> =
        repository.getAllPetEventsByPet(petId)
}