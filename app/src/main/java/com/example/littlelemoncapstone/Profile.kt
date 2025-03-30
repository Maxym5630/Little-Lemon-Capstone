package com.example.littlelemoncapstone

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.core.content.edit

@Composable
fun Profile(navController: NavController) {
    val sharedPreferences = navController.context.getSharedPreferences(
        "user_prefs",
        android.content.Context.MODE_PRIVATE
    )
    Column {
        Text(
            text = "Profile",
            style = typography.displayLarge
        )

        Text(
            text = "First Name: ${sharedPreferences.getString("first_name", "")}",
            style = typography.bodyLarge
        )
        Text(
            text = "Last Name: ${sharedPreferences.getString("last_name", "")}",
            style = typography.bodyLarge
        )
        Text(
            text = "Email: ${sharedPreferences.getString("email", "")}",
            style = typography.bodyLarge
        )

        Button(onClick = {
            // Clear the shared preferences
            sharedPreferences.edit() { clear() }
            navController.navigate(Onboarding.route)
        }) {
            Text(text = "Go to Onboarding")
        }
    }
}