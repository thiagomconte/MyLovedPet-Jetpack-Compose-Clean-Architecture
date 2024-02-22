package com.conte.domain.module.petevent.usecase

import com.conte.domain.module.petevent.repository.PetEventRepository
import java.util.UUID
import javax.inject.Inject

class UpdatePetEventWorkerIdUseCase @Inject constructor(
    private val repository: PetEventRepository
) {

    suspend operator fun invoke(petEventId: Long, workerId: UUID) =
        repository.updatePetEventWorkerId(petEventId, workerId)
}