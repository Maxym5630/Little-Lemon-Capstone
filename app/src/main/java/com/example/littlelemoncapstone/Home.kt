package com.example.littlelemoncapstone

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun Home(navController: NavController) {
    Column {
        Text(
            text = "Home",
            style = typography.displayLarge
        )
        Button(onClick = {
            navController.navigate(Profile.route)
        }) {
            Text(text = "Go to Profile")
        }
    }

}