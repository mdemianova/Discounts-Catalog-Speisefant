package com.ignation.speisefant.domain

import com.ignation.speisefant.R

data class Category(val title: String, val image: Int)

object CategorySource {
    val categories = listOf(
        Category("Fruits", R.drawable.icon_bread_64),
        Category("Milk", R.drawable.icon_bread_64),
        Category("Drinks", R.drawable.icon_bread_64),
        Category("Alco", R.drawable.icon_bread_64)
    )
}
