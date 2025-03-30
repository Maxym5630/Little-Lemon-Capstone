package com.example.littlelemoncapstone

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.littlelemoncapstone.ui.theme.*


@Composable
fun Onboarding(navController: NavController) {
    val sharedPreferences = navController.context.getSharedPreferences(
        "user_prefs",
        android.content.Context.MODE_PRIVATE
    )
    Column {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(top = 40.dp, bottom = 10.dp)
        )
        Text(
            text = "Let's get to know you!",
            modifier = Modifier
                .fillMaxWidth()
                .background(color = primary_one)
                .padding(vertical = 40.dp),

            color = main_white,
            textAlign = TextAlign.Center,
            style = typography.displayMedium
        )
        Text(
            text = "Personal information",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp, bottom = 40.dp, start = 20.dp),

            color = main_black,
            style = typography.bodyLarge
        )

        var firstName by remember { mutableStateOf("") }
        Input(
            name = "First Name",
            placeholderName = "Enter your first name",
            value = firstName,
            setValue = { firstName = it }
        )
        var lastName by remember { mutableStateOf("") }
        Input(
            name = "Last Name",
            placeholderName = "Enter your last name",
            value = lastName,
            setValue = { lastName = it }
        )
        var email by remember { mutableStateOf("") }
        Input(
            name = "Email",
            placeholderName = "Enter your email",
            value = email,
            setValue = { email = it }
        )

        var backgroundColor = primary_two
        var borderColor = Color(0XFFd19630)
        if (!isAllFieldsValid(firstName, lastName, email)) {
            backgroundColor = Gray
            borderColor = Gray
        }

        Button(
            onClick = {
                if (isAllFieldsValid(firstName, lastName, email)) {
                    with(sharedPreferences.edit()) {
                        putString("first_name", firstName)
                        putString("last_name", lastName)
                        putString("email", email)
                        apply()
                    }
                    Toast.makeText(
                        navController.context,
                        "Registration successful",
                        Toast.LENGTH_SHORT
                    ).show()
                    navController.navigate(Home.route)
                } else {
                    //toast registration failed
                    Toast.makeText(
                        navController.context,
                        "Registration failed. Please fill all fields correctly",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            },
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 40.dp)
                .fillMaxWidth()
                .border(1.dp, borderColor, Shapes().small),
            colors = ButtonDefaults.buttonColors(containerColor = backgroundColor),
            shape = Shapes().small,
        ) {
            Text(
                text = "Register",
                modifier = Modifier
                    .padding(vertical = 5.dp),
                color = main_black,
                textAlign = TextAlign.Center,
                style = typography.titleLarge
            )
        }
    }
}

fun isValidEmail(email: String): Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

fun isValidName(name: String): Boolean {
    return name.isNotEmpty() && name.all { it.isLetter() }
}

fun isAllFieldsValid(
    firstName: String,
    lastName: String,
    email: String
): Boolean {
    return isValidName(firstName) && isValidName(lastName) && isValidEmail(email)
}

@Composable
fun Input(name: String, placeholderName: String, value: String, setValue: (String) -> Unit) {
    Column {
        Text(
            text = name,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, bottom = 10.dp, top = 40.dp),
            color = main_black,
            style = typography.bodyMedium
        )
        TextField(
            value = value,
            onValueChange = {
                setValue(it)
            },
            placeholder = {
                Text(
                    text = placeholderName,
                    color = main_black,
                    style = typography.bodyMedium
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .border(1.dp, main_black),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = White,
                unfocusedContainerColor = White
            ),
        )
    }
}