package com.conte.mylovedpet.navigation

sealed class Navigation(val destination: String) {
    object Splash : Navigation("SPLASH")
    object Home : Navigation("HOME")
}

sealed class Route(val value: String) {
    object Main: Navigation("MAIN")
}