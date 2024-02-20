package com.conte.data.module.petevent.mapper

import com.conte.data.module.database.entity.PetEventEntity
import com.conte.domain.module.petevent.model.PetEvent

fun PetEventEntity.toDomain() = PetEvent(id, name, time, petId)

fun List<PetEventEntity>.toDomain() = map { it.toDomain() }

fun PetEvent.toEntity() = PetEventEntity(id, name, time, petId)