package com.conte.data.module.petevent.repository

import app.cash.turbine.test
import com.conte.data.module.database.dao.PetEventDao
import com.conte.data.module.database.entity.PetEntity
import com.conte.data.module.database.entity.PetEventEntity
import com.conte.data.module.database.entity.PetWithEventsEntity
import com.conte.domain.module.pet.model.PetGender
import com.conte.domain.module.pet.model.PetType
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class PetEventLocalDataSourceTest {

    private lateinit var petEventLocalDataSource: PetEventLocalDataSource
    private lateinit var petEventDao: PetEventDao

    @Before
    fun setUp() {
        petEventDao = mockk()
        petEventLocalDataSource = PetEventLocalDataSource(petEventDao)
    }

    @Test
    fun `should call petEventDao insert`() = runBlocking {
        // Given
        coEvery { petEventDao.insert(petEventEntity1) } returns 1L

        // When
        val result = petEventLocalDataSource.insertPetEvent(petEventEntity1)

        // Then
        coVerify { petEventDao.insert(petEventEntity1) }
        assert(result.isSuccess)
        assert(result.getOrNull() == 1L)
    }

    @Test
    fun `should call petEventDao insert and fails`() = runBlocking {
        // Given
        coEvery { petEventDao.insert(petEventEntity1) } throws Exception("Failure to insert")

        // When
        val result = petEventLocalDataSource.insertPetEvent(petEventEntity1)

        // Then
        coVerify { petEventDao.insert(petEventEntity1) }
        assert(result.isFailure)
        assert(result.exceptionOrNull()?.message == "Failure to insert")
    }

    @Test
    fun `should call petEventDao getAllByPet`() = runBlocking {
        // Given
        coEvery { petEventDao.getAllByPet(any()) } returns PetWithEventsEntity(pet = petEntity1, events = listOf(petEventEntity1))

        // When
        val result = petEventLocalDataSource.getAllPetEventsByPet(petEntity1.id)

        // Then
        coVerify { petEventDao.getAllByPet(petEntity1.id) }
        assert(result.isSuccess)
    }

    @Test
    fun `should call petEventDao getAllByPet and fails`() = runBlocking {
        // Given
        coEvery { petEventDao.getAllByPet(any()) } throws Exception("Failure to get events")

        // When
        val result = petEventLocalDataSource.getAllPetEventsByPet(petEntity1.id)

        // Then
        coVerify { petEventDao.getAllByPet(petEntity1.id) }
        assert(result.isFailure)
        assert(result.exceptionOrNull()?.message == "Failure to get events")
    }

    @Test
    fun `should call petEventDao flowAllByPet`() = runBlocking {
        // Given
        every { petEventDao.flowAllByPet(any()) } returns flow {
            emit(PetWithEventsEntity(pet = petEntity1, events = listOf(petEventEntity1)))
        }

        // When
        val flow: Flow<PetWithEventsEntity> = petEventLocalDataSource.flowAllPetEventsByPet(petEntity1.id)

        // Then
        flow.test {
            val item1 = this.awaitItem()
            assert(item1.events.size == 1)
            awaitComplete()
        }
        coVerify {
            petEventDao.flowAllByPet(any())
        }
    }

    @Test
    fun `should call petEventDao getById`() = runBlocking {
        // Given
        coEvery { petEventDao.getById(any()) } returns petEventEntity1

        // When
        val result = petEventLocalDataSource.getPetEventById(petEntity1.id)

        // Then
        coVerify { petEventDao.getById(any()) }
        assert(result.isSuccess)
    }

    @Test
    fun `should call petEventDao getById and fails`() = runBlocking {
        // Given
        coEvery { petEventDao.getById(any()) } throws Exception("Failure to get event")

        // When
        val result = petEventLocalDataSource.getPetEventById(petEntity1.id)

        // Then
        coVerify { petEventDao.getById(petEntity1.id) }
        assert(result.isFailure)
        assert(result.exceptionOrNull()?.message == "Failure to get event")
    }

    @Test
    fun `should call petEventDao delete`() = runBlocking {
        // Given
        coEvery { petEventDao.delete(petEventEntity1) } returns Unit

        // When
        petEventLocalDataSource.deletePetEvent(petEventEntity1)

        // Then
        coVerify { petEventDao.delete(petEventEntity1) }
    }

    @Test
    fun `should call petEventDao delete and fails`() = runBlocking {
        // Given
        coEvery { petEventDao.delete(petEventEntity1) } throws Exception("Failed to delete")

        // When
        val result = petEventLocalDataSource.deletePetEvent(petEventEntity1)

        // Then
        coVerify { petEventDao.delete(petEventEntity1) }
        assert(result.isFailure)
        assert(result.exceptionOrNull()?.message == "Failed to delete")
    }

    @Test
    fun `should call petEventDao updatePetEventWorkerId`() = runBlocking {
        // Given
        coEvery { petEventDao.updatePetEventWorkerId(any(), any()) } returns Unit

        // When
        val result = petEventLocalDataSource.updatePetEventWorkerId(petEntity1.id.toLong(), "random_worker_id")

        // Then
        coVerify { petEventDao.updatePetEventWorkerId(any(), any()) }
        assert(result.isSuccess)
    }

    @Test
    fun `should call petEventDao updatePetEventWorkerId and fails`() = runBlocking {
        // Given
        coEvery { petEventDao.updatePetEventWorkerId(any(), any()) } throws Exception("Failed to update")

        // When
        val result = petEventLocalDataSource.updatePetEventWorkerId(petEntity1.id.toLong(), "random_worker_id")

        // Then
        coVerify { petEventDao.updatePetEventWorkerId(any(), any()) }
        assert(result.isFailure)
        assert(result.exceptionOrNull()?.message == "Failed to update")
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
        val petEventEntity1 = PetEventEntity(id = 1, name = "Vacina", time = 1708803478, petId = 1, workerId = null)
    }
}