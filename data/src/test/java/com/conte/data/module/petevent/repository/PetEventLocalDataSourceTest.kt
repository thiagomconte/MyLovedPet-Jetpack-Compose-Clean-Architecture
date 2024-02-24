package com.conte.data.module.petevent.repository

import com.conte.data.module.database.dao.PetEventDao
import com.conte.data.module.database.entity.PetEventEntity
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class PetEventLocalDataSourceTest {

    private lateinit var petEventLocalDataSource: PetEventLocalDataSource
    private lateinit var petEventDao: PetEventDao

    @Before
    fun setUp() {
        petEventDao = mockk()
        petEventLocalDataSource = PetEventLocalDataSource(petEventDao)
    }

    @Test
    fun `insert should call petEventDao insert`() = runBlocking {
        // Given
        coEvery { petEventDao.insert(petEventEntity1) } returns 1L

        // When
        val result = petEventLocalDataSource.insertPetEvent(petEventEntity1)

        // Then
        coVerify { petEventDao.insert(petEventEntity1) }
        assert(result.isSuccess)
        assert(result.getOrNull() == 1L)
    }

    companion object {
        val petEventEntity1 = PetEventEntity(id = 1, name = "Vacina", time = 1708803478, petId = 1, workerId = null)
    }
}