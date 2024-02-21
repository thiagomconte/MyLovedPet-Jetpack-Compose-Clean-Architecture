package com.conte.domain.module.petevent.usecase

import com.conte.domain.module.petevent.repository.PetEventRepository
import javax.inject.Inject

class UpdatePetEventNotificationIdUseCase @Inject constructor(
    private val repository: PetEventRepository
) {

    suspend operator fun invoke(petEventId: Long, notificationId: Int) =
        repository.updatePetEventNotificationId(petEventId, notificationId)
}