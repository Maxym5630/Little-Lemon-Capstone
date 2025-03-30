package com.example.littlelemoncapstone

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.littlelemoncapstone.ui.theme.main_black
import com.example.littlelemoncapstone.ui.theme.main_white
import com.example.littlelemoncapstone.ui.theme.primary_one
import com.example.littlelemoncapstone.ui.theme.primary_two

@Composable
fun Profile(navController: NavController) {
    val sharedPreferences = navController.context.getSharedPreferences(
        "user_prefs",
        android.content.Context.MODE_PRIVATE
    )
    val firstName = sharedPreferences.getString("first_name", "").toString()
    val lastName = sharedPreferences.getString("last_name", "").toString()
    val email = sharedPreferences.getString("email", "").toString()
    Column (
        modifier = Modifier.fillMaxSize()
            .padding(bottom = 20.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp, bottom = 100.dp)
                .height(50.dp)
        )

        Text(
            text = "Personal information",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp, bottom = 40.dp, start = 20.dp),

            color = main_black,
            style = typography.bodyLarge
        )

        Input(
            name = "First Name",
            placeholderName = "Enter your first name",
            value = firstName,
            setValue = {  },
            readOnly = true
        )
        Input(
            name = "Last Name",
            placeholderName = "Enter your last name",
            value = lastName,
            setValue = {  },
            readOnly = true
        )
        Input(
            name = "Email",
            placeholderName = "Enter your email",
            value = email,
            setValue = {  },
            readOnly = true
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {
                with(sharedPreferences.edit()) {
                    clear()
                    apply()
                }
                navController.navigate(Onboarding.route) {
                    popUpTo(Onboarding.route) {
                        inclusive = true
                    }
                }
            },
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 40.dp)
                .fillMaxWidth()
                .border(1.dp, Color(0XFFd19630), Shapes().small),
            colors = ButtonDefaults.buttonColors(containerColor = primary_two),
            shape = Shapes().small,
        ) {
            Text(
                text = "Log out",
                modifier = Modifier
                    .padding(vertical = 5.dp),
                color = main_black,
                textAlign = TextAlign.Center,
                style = typography.titleLarge
            )
        }
    }
}