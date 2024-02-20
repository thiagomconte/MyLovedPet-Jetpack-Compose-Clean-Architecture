package com.conte.domain.module.petevent.usecase

import com.conte.domain.module.pet.model.PetWithEvents
import com.conte.domain.module.petevent.repository.PetEventRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FlowAllPetEventsByPetUseCase @Inject constructor(
    private val repository: PetEventRepository
) {

    suspend operator fun invoke(petId: Int): Flow<PetWithEvents> =
        repository.flowAllPetEventsByPet(petId)

}