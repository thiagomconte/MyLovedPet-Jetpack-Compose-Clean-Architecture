package com.conte.data.module.pet.mapper

import com.conte.data.module.database.entity.PetWithEventsEntity
import com.conte.data.module.petevent.mapper.toDomain
import com.conte.domain.module.pet.model.PetWithEvents

fun PetWithEventsEntity.toPetWithEvents() = PetWithEvents(
    pet = pet.toDomain(),
    events = events.map { it.toDomain() }.sortedBy { it.time }
)