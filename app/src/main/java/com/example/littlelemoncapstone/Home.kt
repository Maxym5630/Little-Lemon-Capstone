package com.example.littlelemoncapstone

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.room.Room
import com.example.littlelemoncapstone.ui.theme.main_black
import com.example.littlelemoncapstone.ui.theme.main_white
import com.example.littlelemoncapstone.ui.theme.primary_one
import com.example.littlelemoncapstone.ui.theme.primary_two

@Composable
fun Home(navController: NavController) {
    val database = Room.databaseBuilder(
        navController.context,
        AppDatabase::class.java,
        "database"
    ).build()

    val catList by database.menuItemDao().getAllCategories()
        .collectAsState(initial = emptyList())


    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Header(navController = navController)

        Hero()

        Row {
            CategoryItems(items = catList)
        }
        Text(
            text = "Home",
            style = typography.displayLarge
        )
    }

}


@Composable
fun Header(navController: NavController) {
    Row(
        modifier = Modifier
            .padding(top = 40.dp, bottom = 10.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(
            modifier = Modifier
                .width(50.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier
                .height(56.dp)
                .width((185 / 40 * 56).dp)
        )
        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = "Profile",
            modifier = Modifier
                .height(50.dp)
                .clickable { navController.navigate(Profile.route) }
        )
    }
}

@Composable
fun Hero() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(324.dp)
            .background(primary_one)
            .padding(start = 25.dp, end = 25.dp),
    ) {
        Text(
            text = "Little Lemon",
            style = typography.displayLarge,
            color = primary_two
        )
        Row {
            Column(
                modifier = Modifier
                    .width(240.dp)

            ) {
                Text(
                    text = "Chicago",
                    style = typography.displayMedium,
                    color = main_white
                )
                Text(
                    text = "We are a family owned Mediterranean restaurant, focused on" +
                            " traditional recipes served with a modern twist.",
                    style = typography.titleLarge,
                    color = main_white
                )
            }
            Image(
                painter = painterResource(id = R.drawable.hero_image),
                contentDescription = "Hero",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(140.dp)
                    .height(151.dp)
                    .clip(RectangleShape)
                    .padding(top = 40.dp)
            )
        }
        var text by remember { mutableStateOf("") }

        TextField(
            value = text,
            onValueChange = { text = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 19.dp)
                .background(main_white),
            placeholder = { Text("Поиск...") },
            leadingIcon = {
                Icon(imageVector = Icons.Default.Search, contentDescription = "Поиск")
            }
        )
    }

}

@Composable
fun CategoryItems(items: List<String>) {
    Column {
        Text(
            text = "Order for Delivery!".uppercase(),
            style = typography.titleMedium,
            color = main_black,
            modifier = Modifier
                .padding(start = 26.dp, top = 21.dp, bottom = 43.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items.forEach { item ->
                Text(
                    text = item.replaceFirstChar { it.uppercase() },
                    style = typography.titleSmall,
                    color = primary_one,

                    modifier = Modifier
                        .clickable { /* Handle click */ }
                        .clip(RoundedCornerShape(20.dp))
                        .background(main_white)
                        .padding(vertical = 8.dp, horizontal = 16.dp)
                )
            }
        }
    }
}
