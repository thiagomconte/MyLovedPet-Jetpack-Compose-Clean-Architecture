package com.conte.domain.module.pet.usecase

import com.conte.domain.module.pet.model.Pet
import com.conte.domain.module.pet.repository.PetRepository
import javax.inject.Inject

class GetAllPetsUseCase @Inject constructor(
    private val repository: PetRepository
) {

    suspend operator fun invoke(): Result<List<Pet>> = repository.getAll()
}