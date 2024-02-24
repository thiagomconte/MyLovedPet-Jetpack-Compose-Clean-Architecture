package com.conte.data.module.pet.dao

import androidx.test.ext.junit.runners.AndroidJUnit4
import app.cash.turbine.test
import com.conte.data.module.BaseTest
import com.conte.data.module.database.entity.PetEntity
import com.conte.domain.module.pet.model.PetGender
import com.conte.domain.module.pet.model.PetType
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PetDaoTest : BaseTest() {

    @Test
    fun shouldInsertPet() = runTest {
        // given
        val dao = db.petDao()

        // when
        dao.insert(listOf(petEntity1, petEntity2))

        // then
        val result = dao.getAll()
        assert(result.size == 2)
        assert(result.first().id == petEntity1.id)
    }

    @Test
    fun shouldFlowAllPets() = runTest {
        // given
        val dao = db.petDao()

        // when - then
        dao.insert(petEntity1)
        dao.flowAll().test {
            val item = awaitItem()
            assert(item.first().id == petEntity1.id)
            dao.insert(petEntity2)
            val item2 = awaitItem()
            assert(item2[1].id == petEntity2.id)
            cancel()
        }
    }

    @Test
    fun shouldGetPetById() = runTest {
        // given
        val dao = db.petDao()

        // when
        dao.insert(listOf(petEntity1, petEntity2))

        // then
        val result = dao.getById(petEntity2.id)
        assert(result.name == petEntity2.name)
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