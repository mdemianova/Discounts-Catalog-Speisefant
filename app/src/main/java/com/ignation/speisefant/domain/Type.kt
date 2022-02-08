package com.ignation.speisefant.domain

import com.ignation.speisefant.R

data class Type(val title: String, val image: Int)

object TypeSource {
    val types = listOf(
        Type("Fruits", R.drawable.icon_bread_64),
        Type("Milk", R.drawable.icon_bread_64),
        Type("Drinks", R.drawable.icon_bread_64),
        Type("Alco", R.drawable.icon_bread_64)
    )
}
