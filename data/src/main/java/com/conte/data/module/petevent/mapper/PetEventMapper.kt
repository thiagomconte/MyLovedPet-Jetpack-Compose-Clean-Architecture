package com.conte.data.module.petevent.mapper

import com.conte.data.module.database.entity.PetEventEntity
import com.conte.domain.module.petevent.model.PetEvent
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

fun PetEventEntity.toDomain(): PetEvent {
    val instant = Instant.ofEpochMilli(time)
    val localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
    val formatter = DateTimeFormatter.ofPattern("ddMMyyyyHHmm")
    val longToDateTimeString = localDateTime.format(formatter)
    return PetEvent(id, name, longToDateTimeString, petId)
}

fun List<PetEventEntity>.toDomain() = map { it.toDomain() }

fun PetEvent.toEntity(): PetEventEntity {
    val formatter = DateTimeFormatter.ofPattern("ddMMyyyyHHmm")
    val localDateTime = LocalDateTime.parse(time, formatter)
    val timeToLong =
        localDateTime.toEpochSecond(java.time.ZoneOffset.UTC) * 1000 // Convertendo para milissegundos
    return PetEventEntity(id, name, timeToLong, petId, notificationId)
}