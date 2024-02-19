package com.conte.domain.module.pet.model

import com.conte.domain.module.event.model.PetEvent

data class PetWithEvents(
    val pet: Pet,
    val events: List<PetEvent>
)