package com.conte.mylovedpet.navigation

private const val SLASH = "/"

sealed class Navigation(val destination: String) {
    object Splash : Navigation("splash")
    object Home : Navigation("home")
    object AddPet : Navigation("addPet")
    object PetEvent : Navigation("petEvent") {
        const val petId = "petId"
        fun PetEvent.destinationWithParams() = this.destination.plus(SLASH).plus("{$petId}")
        fun PetEvent.navigateParams(id: Int) = this.destination.plus(SLASH).plus(id.toString())
    }

    object AddPetEvent : Navigation("addPetEvent") {
        const val petId = "petId"
        const val petName = "petName"
        fun AddPetEvent.destinationWithParams() =
            this.destination + SLASH + "{$petId}" + SLASH + "{$petName}"

        fun AddPetEvent.navigateParams(id: Int, name: String) =
            this.destination + SLASH + id.toString() + SLASH + name
    }
}

sealed class Route(val value: String) {
    object Main : Navigation("main")
}