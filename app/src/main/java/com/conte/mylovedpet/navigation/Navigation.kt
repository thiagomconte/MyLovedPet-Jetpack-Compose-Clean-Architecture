package com.conte.mylovedpet.navigation

sealed class Navigation(val destination: String) {
    object Splash : Navigation("splash")
    object Home : Navigation("home")
    object AddPet : Navigation("addPet")
    object AddEvent : Navigation("addEvent/{petId}") {
        const val param = "petId"
    }
}

sealed class Route(val value: String) {
    object Main : Navigation("main")
}