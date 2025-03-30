package com.example.littlelemoncapstone


import kotlinx.serialization.Serializable

@Serializable
data class MenuNetworkData(
    val menu: List<MenuItemNetwork>
)

@Serializable
data class MenuItemNetwork(
    val id: Int,
    val title: String,
    val description: String,
    val price: String,
    val image: String,
    val category: String,
) {
    fun toMenuItemRoom() = MenuItemRoom(
        id = id,
        title = title,
        description = description,
        price = price.toDouble(),
        image = image,
        category = category
    )
}

