package com.ignation.speisefant.domain

import androidx.annotation.DrawableRes
import com.ignation.speisefant.R

data class Shop(
    @DrawableRes val imageResId: Int,
    val title: String
)

object ShopSource {
    val shops: List<Shop> = listOf(
        Shop(R.drawable.rewe_image, "Rewe"),
        Shop(R.drawable.aldi_sued, "Aldi Süd"),
        Shop(R.drawable.edeka, "Edeka"),
        Shop(R.drawable.lidl, "Lidl")
    )

}
