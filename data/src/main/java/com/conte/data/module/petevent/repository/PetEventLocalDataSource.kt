package com.conte.data.module.petevent.repository

import com.conte.data.module.database.dao.PetEventDao
import com.conte.data.module.database.entity.PetWithEventsEntity
import com.conte.domain.module.commons.logError
import javax.inject.Inject

class PetEventLocalDataSource @Inject constructor(
    private val dao: PetEventDao
) {

    suspend fun getAllEventsByPet(petId: Int): Result<PetWithEventsEntity> = runCatching {
        dao.getAllByPet(petId)
    }.onFailure {
        logError { "Failure to getAllEventsByPet($petId) on EventLocalDataSource. $it" }
    }
}