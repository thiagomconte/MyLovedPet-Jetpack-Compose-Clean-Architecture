package com.conte.data.module.pet.repository

import app.cash.turbine.test
import com.conte.data.module.database.dao.PetDao
import com.conte.data.module.database.entity.PetEntity
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
class PetLocalDataSourceTest {

    private lateinit var petLocalDataSource: PetLocalDataSource
    private lateinit var petDao: PetDao

    @Before
    fun setUp() {
        petDao = mockk()
        petLocalDataSource = PetLocalDataSource(petDao)
    }

    @Test
    fun `insert should call petDao insert`() = runBlocking {
        // Given
        coEvery { petDao.insert(petEntity1) } returns 1L

        // When
        val result = petLocalDataSource.insert(petEntity1)

        // Then
        coVerify { petDao.insert(petEntity1) }
        assert(result.isSuccess)
        assert(result.getOrNull() == 1L)
    }

    @Test
    fun `insert should return failure when petDao insert fails`() = runBlocking {
        // Given
        coEvery { petDao.insert(petEntity1) } throws Exception("Failed to insert pet")

        // When
        val result = petLocalDataSource.insert(petEntity1)

        // Then
        coVerify { petDao.insert(petEntity1) }
        assert(result.isFailure)
        assert(result.exceptionOrNull()?.message == "Failed to insert pet")
    }

    @Test
    fun `should call petDao getAll`() = runBlocking {
        // Given
        coEvery { petDao.getAll() } returns listOf(petEntity1, petEntity2)

        // When
        val result = petLocalDataSource.getAll()

        // Then
        coVerify { petDao.getAll() }
        assert(result.isSuccess)
        assert(result.getOrNull()?.size == 2)
    }

    @Test
    fun `should return failure when petDao getAll fails`() = runBlocking {
        // Given
        coEvery { petDao.getAll() } throws Exception("Failed to getAll pets")

        // When
        val result = petLocalDataSource.getAll()

        // Then
        coVerify { petDao.getAll() }
        assert(result.isFailure)
        assert(result.exceptionOrNull()?.message == "Failed to getAll pets")
    }

    @Test
    fun `should call petDao getById`() = runBlocking {
        // Given
        coEvery { petDao.getById(2) } returns petEntity2

        // When
        val result = petLocalDataSource.getById(2)

        // Then
        coVerify { petDao.getById(2) }
        assert(result.isSuccess)
        assert(result.getOrNull()?.id == petEntity2.id)
    }

    @Test
    fun `should return failure when petDao getById fails`() = runBlocking {
        // Given
        coEvery { petDao.getById(any()) } throws Exception("Failed to getById")

        // When
        val result = petLocalDataSource.getById(2)

        // Then
        coVerify { petDao.getById(any()) }
        assert(result.isFailure)
        assert(result.exceptionOrNull()?.message == "Failed to getById")
    }

    @Test
    fun `should call petDao flowAll`() = runBlocking {
        // Given
        every { petDao.flowAll() } returns flow {
            emit(listOf(petEntity1, petEntity2))
        }

        // When
        val flow: Flow<List<PetEntity>> = petLocalDataSource.flowAll()

        // Then
        flow.test {
            val item1 = this.awaitItem()
            assert(item1.size == 2)
            awaitComplete()
        }
        coVerify {
            petDao.flowAll()
        }
    }

    @Test
    fun `should call petDao update`() = runBlocking {
        // Given
        val newPetEntity1 = petEntity1.copy(name = "NewName")
        coEvery { petDao.update(newPetEntity1) } returns Unit

        // When
        val result = petLocalDataSource.update(newPetEntity1)

        // Then
        coVerify { petDao.update(newPetEntity1) }
        assert(result.isSuccess)
    }

    @Test
    fun `should return failure when petDao update fails`() = runBlocking {
        // Given
        val newPetEntity1 = petEntity1.copy(name = "NewName")
        coEvery { petDao.update(newPetEntity1) } throws Exception("Failed to update")

        // When
        val result = petLocalDataSource.update(newPetEntity1)

        // Then
        coVerify { petDao.update(newPetEntity1) }
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
        val petEntity2 = PetEntity(
            id = 2,
            name = "Thor",
            birthday = "20022022",
            breed = "Pitbull",
            type = PetType.DOG,
            gender = PetGender.MALE,
            image = null
        )
    }
}