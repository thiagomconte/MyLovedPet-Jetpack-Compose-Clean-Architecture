package com.conte.domain.module.petevent.model

data class PetEvent(
    val id: Int = 0,
    val name: String,
    val time: String,
    val petId: Int,
    val notificationId: Int = -1,
) {

    companion object {
        fun buildFake() = PetEvent(id = 7349, name = "Elsa", time = "", petId = 7264, notificationId = 9340)
    }
}
