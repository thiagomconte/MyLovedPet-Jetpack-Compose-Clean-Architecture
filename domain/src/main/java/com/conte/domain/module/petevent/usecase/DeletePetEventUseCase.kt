package com.conte.domain.module.petevent.usecase

import com.conte.domain.module.petevent.model.PetEvent
import com.conte.domain.module.petevent.repository.PetEventRepository
import javax.inject.Inject

class DeletePetEventUseCase @Inject constructor(
    private val repository: PetEventRepository
) {

    suspend operator fun invoke(petEvent: PetEvent) = repository.deletePetEvent(petEvent)
}