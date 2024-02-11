package com.conte.domain.module.pet.usecase

import com.conte.domain.module.pet.model.Pet
import com.conte.domain.module.pet.repository.PetRepository
import javax.inject.Inject

class InsertPetUseCase @Inject constructor(
    private val repository: PetRepository
) {

    suspend operator fun invoke(pet: Pet): Result<Unit> = repository.insert(pet)
}