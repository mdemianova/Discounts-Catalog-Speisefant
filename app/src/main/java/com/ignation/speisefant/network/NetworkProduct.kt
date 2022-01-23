package com.ignation.speisefant.network

import com.ignation.speisefant.database.ProductDatabase
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NetworkProduct(
    val name: String,
    @Json(name = "image_url")
    val imageUrl: String,
    @Json(name = "new_price")
    val newPrice: Float,
    @Json(name = "old_price")
    val oldPrice: Float,
    val shop: String,
    @Json(name = "start_date")
    val startDate: String,
    @Json(name = "end_date")
    val endDate: String,
    val type: String
)

@JsonClass(generateAdapter = true)
data class NetworkProductList(val products: List<NetworkProduct>)

fun NetworkProductList.asDatabaseModel(): List<ProductDatabase> {
    return products.map {
        ProductDatabase(
            name = it.name,
            imageUrl = it.imageUrl,
            newPrice = it.newPrice,
            oldPrice = it.oldPrice,
            shop = it.shop,
            startDate = it.startDate,
            endDate = it.endDate,
            type = it.type
        )
    }
}