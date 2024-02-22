package com.conte.domain.module.petevent.model

import java.util.UUID

data class PetEvent(
    val id: Int = 0,
    val name: String,
    val time: String,
    val petId: Int,
    val workerId: UUID?,
) {

    companion object {
        fun buildFake() = PetEvent(id = 7349, name = "Elsa", time = "220220241300", petId = 7264, UUID.randomUUID())
    }
}
