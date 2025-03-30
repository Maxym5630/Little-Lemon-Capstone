package com.example.littlelemoncapstone

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MyNavigation() {
    val navController = rememberNavController()
    val sharedPreferences = navController.context.getSharedPreferences(
        "user_prefs",
        android.content.Context.MODE_PRIVATE
    )
    val firstName = sharedPreferences.getString("first_name", "")
    val startDestination = if (firstName.isNullOrEmpty()) {
        Onboarding.route
    } else {
        Home.route
    }
    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        composable(Home.route) {
            Home(navController = navController)
        }
        composable(Onboarding.route) {
            Onboarding(navController = navController)
        }
        composable(Profile.route) {
            Profile(navController = navController)
        }

    }
}

