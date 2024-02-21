package com.conte.data.module.petevent.repository

import com.conte.data.module.database.dao.BaseDao.Companion.ROW_NOT_INSERTED
import com.conte.data.module.database.dao.PetEventDao
import com.conte.data.module.database.entity.PetEventEntity
import com.conte.data.module.database.entity.PetWithEventsEntity
import com.conte.data.module.database.exceptions.DatabaseException
import com.conte.domain.module.commons.logError
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PetEventLocalDataSource @Inject constructor(
    private val dao: PetEventDao
) {

    suspend fun getAllEventsByPet(petId: Int): Result<PetWithEventsEntity> = runCatching {
        dao.getAllByPet(petId)
    }.onFailure {
        logError { "Failure to getAllEventsByPet($petId) on PetEventLocalDataSource. $it" }
    }

    suspend fun insertPetEvent(petEventEntity: PetEventEntity): Result<Long> = runCatching {
        val resultId = dao.insert(petEventEntity)
        if (resultId == ROW_NOT_INSERTED) throw DatabaseException("Failure to insert PetEventEntity")
        resultId
    }.onFailure {
        logError { "Failure to insert on PetEventLocalDataSource. $it" }
    }

    fun flowAllEventsByPet(petId: Int): Flow<PetWithEventsEntity> = dao.flowAllByPet(petId)

    suspend fun updatePetEventNotificationId(petEventId: Long, notificationId: Int): Result<Unit> =
        runCatching {
            dao.updatePetEventNotificationId(petEventId, notificationId)
        }

    suspend fun getEventById(id: Int): Result<PetEventEntity> = runCatching {
        dao.getById(id)
    }.onFailure {
        logError { "Failure to getEventById($id) on PetEventLocalDataSource. $it" }
    }
}