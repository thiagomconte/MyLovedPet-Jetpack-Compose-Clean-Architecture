package com.conte.domain.module.pet.usecase

import com.conte.domain.module.pet.repository.PetRepository
import javax.inject.Inject

class GetPetByIdUseCase @Inject constructor(
    private val repository: PetRepository
) {

    suspend operator fun invoke(id: Int) = repository.getById(id)
}