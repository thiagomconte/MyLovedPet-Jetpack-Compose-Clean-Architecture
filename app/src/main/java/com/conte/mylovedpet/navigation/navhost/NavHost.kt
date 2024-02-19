package com.conte.mylovedpet.navigation.navhost

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.conte.mylovedpet.navigation.Navigation
import com.conte.mylovedpet.navigation.Route
import com.conte.mylovedpet.presentation.addpet.AddPetScreen
import com.conte.mylovedpet.presentation.home.HomeScreen
import com.conte.mylovedpet.presentation.petevent.PetEventScreen
import com.conte.mylovedpet.presentation.splash.SplashScreen

@Composable
fun NavHost(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(navController = navController, startDestination = Navigation.Splash.destination) {
        composable(Navigation.Splash.destination) {
            SplashScreen(onNavigate = { navController.navigate(Navigation.Home.destination) })
        }
        mainNavigation(navController)
    }
}


private fun NavGraphBuilder.mainNavigation(navController: NavHostController) {
    navigation(startDestination = Navigation.Home.destination, route = Route.Main.destination) {
        composable(Navigation.Home.destination) {
            HomeScreen(navController = navController)
        }
        composable(Navigation.AddPet.destination) {
            AddPetScreen(navController = navController)
        }
        composable(
            Navigation.AddEvent.destination,
            arguments = listOf(navArgument(Navigation.AddEvent.param) {
                type = NavType.StringType
            })
        ) {
            PetEventScreen(navController = navController)
        }
    }
}