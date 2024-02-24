package com.conte.data.module.petevent.dao

import androidx.test.ext.junit.runners.AndroidJUnit4
import app.cash.turbine.test
import com.conte.data.module.BaseTest
import com.conte.data.module.database.entity.PetEntity
import com.conte.data.module.database.entity.PetEventEntity
import com.conte.domain.module.pet.model.PetGender
import com.conte.domain.module.pet.model.PetType
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PetEventDaoTest : BaseTest() {

    private fun insertPets() = runBlocking {
        val petDao = db.petDao()
        petDao.insert(listOf(petEntity1, petEntity2))
    }

    @Test
    fun shouldInsertAndGetPetEvent() = runTest {
        // given
        val petEventDao = db.eventDao()

        // when
        insertPets()
        petEventDao.insert(petEventEntity1)

        // then
        val result = petEventDao.getAllByPet(petEntity1.id)
        assert(result.pet.id == petEntity1.id)
    }

    @Test
    fun shouldFlowAllPetEvents() = runTest {
        // given
        val petEventDao = db.eventDao()

        // when - then
        insertPets()
        petEventDao.insert(petEventEntity1)
        petEventDao.flowAllByPet(petEntity1.id).test {
            val item = awaitItem()
            assert(item.pet.id == petEntity1.id)
            cancel()
        }
    }

    @Test
    fun shouldGetPetEventById() = runTest {
        // given
        val petEventDao = db.eventDao()

        // when
        insertPets()
        petEventDao.insert(petEventEntity1)

        // then
        val result = petEventDao.getById(petEventEntity1.id)
        assert(result.id == petEventEntity1.id)
        assert(result.name == petEventEntity1.name)
        assert(result.time == petEventEntity1.time)
        assert(result.petId == petEventEntity1.petId)
        assert(result.workerId == petEventEntity1.workerId)
    }

    @Test
    fun shouldUpdateWorkerId() = runTest {
        // given
        val petEventDao = db.eventDao()
        val workerId = "random_workerId"

        // when
        insertPets()
        petEventDao.insert(petEventEntity1)
        petEventDao.updatePetEventWorkerId(petEventEntity1.id.toLong(), workerId = workerId)

        // then
        val result = petEventDao.getById(petEventEntity1.id)
        assert(result.workerId == workerId)
    }

    companion object {
        val petEntity1 = PetEntity(
            id = 1,
            name = "Ted",
            birthday = "20022024",
            breed = "Golden",
            type = PetType.DOG,
            gender = PetGender.MALE,
            image = null
        )
        val petEntity2 = PetEntity(
            id = 2,
            name = "Thor",
            birthday = "20022022",
            breed = "Pitbull",
            type = PetType.DOG,
            gender = PetGender.MALE,
            image = null
        )
        val petEventEntity1 = PetEventEntity(id = 1, name = "Vacina", time = 1708803478, petId = 1, workerId = null)
    }
}