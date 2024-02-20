package com.conte.domain.module.petevent.model

data class PetEvent(
    val id: Int = 0,
    val name: String,
    val time: String,
    val petId: Int,
)
