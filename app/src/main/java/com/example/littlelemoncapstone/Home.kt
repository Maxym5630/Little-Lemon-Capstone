package com.example.littlelemoncapstone

import android.util.Log
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.platform.LocalContext
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


    val itemList by database.menuItemDao().getAllFlow()
        .collectAsState(initial = emptyList())

    val catList by database.menuItemDao().getAllCategories()
        .collectAsState(initial = emptyList())

    fun clearCategory(b: Boolean) {

    }
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Header(navController = navController)

        var searchPhrase by remember { mutableStateOf("") }
        Hero(searchPhrase = searchPhrase, setSearchPhrase = { searchPhrase = it })

        var isStarters by remember { mutableStateOf(false) }
        var isMains by remember { mutableStateOf(false) }
        var isDeserts by remember { mutableStateOf(false) }
        var isAll by remember { mutableStateOf(true) }

        fun clearCategory(startAll: Boolean = false) {
            isStarters = false
            isMains = false
            isDeserts = false
            isAll = startAll
        }

        fun isFiltered(): Boolean {
            return isStarters || isMains || isDeserts
        }

        val setCategory = { category: String ->
            Log.d("category", "Home: $category")
            clearCategory(false)
            when (category) {
                "starters" -> isStarters = true
                "mains" -> isMains = true
                "desserts" -> isDeserts = true
                "all" -> isAll = true
            }
        }

        val filter = mapOf(
            "starters" to isStarters,
            "mains" to isMains,
            "desserts" to isDeserts,
            "all" to isAll
        )

        CategoryItems(items = catList, setCategory = setCategory, filter = filter)

        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .padding(bottom = 21.dp),
            color = main_white
        )

        var filteredItems = if (searchPhrase.isNotEmpty()) {
            itemList.filter { it.title.contains(searchPhrase, ignoreCase = true) }
        } else {
            itemList
        }
        if (isStarters) {
            filteredItems = filteredItems.filter { it.category == "starters" }
        }
        if (isMains) {
            filteredItems = filteredItems.filter { it.category == "mains" }
        }
        if (isDeserts) {
            filteredItems = filteredItems.filter { it.category == "desserts" }
        }



        MenuItems(items = filteredItems)

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
fun Hero(searchPhrase: String, setSearchPhrase: (String) -> Unit) {
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

        TextField(
            value = searchPhrase,
            onValueChange = setSearchPhrase,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 19.dp)
                .background(main_white),
            placeholder = { Text("Enter Search Phrase") },
            leadingIcon = {
                Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
            }
        )
    }

}

@Composable
fun CategoryItems(
    items: List<String>,
    setCategory: (String) -> Unit,
    filter: Map<String, Boolean>
) {
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
            ItemComponent("all", setCategory, filter)
            items.forEach { item ->
                ItemComponent(item, setCategory, filter)
            }
        }
    }
}

@Composable
fun ItemComponent(item: String, setCategory: (String) -> Unit, filter: Map<String, Boolean>) {

    var backgroundColor = main_white
    var textColor = primary_one
    if (filter[item] == true) {
        backgroundColor = primary_two
        textColor = main_black
    }

    Text(
        text = item.replaceFirstChar { it.uppercase() },
        style = typography.titleSmall,
        color = textColor,
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(backgroundColor)
            .padding(vertical = 8.dp, horizontal = 16.dp)
            .clickable { setCategory(item) }
    )
}

@Composable
fun MenuItems(items: List<MenuItemRoom>) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        items(items) { item ->
            MenuItem(item)
        }

    }

}

@Composable
fun MenuItem(item: MenuItemRoom) {
    val imageName = item.title.replace(" ", "").lowercase()
    val context = LocalContext.current
    val resId = context.resources.getIdentifier(imageName, "drawable", context.packageName)
    Column(
        modifier = Modifier
            .padding(horizontal = 25.dp, vertical = 21.dp)
            .fillMaxSize()
    ) {
        Text(
            text = item.title,
            style = typography.bodyLarge,
            color = main_black,
        )
        Row {
            Column(
                modifier = Modifier
                    .width(277.dp)

            ) {
                Text(
                    text = item.description,
                    style = typography.bodyMedium,
                    color = primary_one,
                    modifier = Modifier
                        .height(47.dp)
                )
                Text(
                    text = "$${item.price}",
                    style = typography.labelLarge,
                    color = primary_one,
                    modifier = Modifier
                        .padding(top = 17.dp)
                )
            }

            Image(
                painter = painterResource(id = resId),
                contentDescription = "Pasta image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(80.dp)
                    .height(80.dp)

            )
        }
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .padding(top = 21.dp),
            color = main_white
        )
    }
}
