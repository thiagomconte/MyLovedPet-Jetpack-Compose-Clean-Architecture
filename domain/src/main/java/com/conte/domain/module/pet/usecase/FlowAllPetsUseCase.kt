package com.conte.domain.module.pet.usecase

import com.conte.domain.module.pet.model.Pet
import com.conte.domain.module.pet.repository.PetRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FlowAllPetsUseCase @Inject constructor(
    private val repository: PetRepository
) {

    operator fun invoke(): Flow<List<Pet>> = repository.flowAll()
}