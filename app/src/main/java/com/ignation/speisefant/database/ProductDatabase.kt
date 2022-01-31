package com.ignation.speisefant.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.ignation.speisefant.domain.Product

@Entity(tableName = "database_product", indices = [Index(value = ["name", "image_url"])])
data class ProductDatabase(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    val name: String,
    @ColumnInfo(name = "image_url")
    val imageUrl: String,
    @ColumnInfo(name = "new_price")
    val newPrice: Int,
    @ColumnInfo(name = "old_price")
    val oldPrice: Int,
    val shop: String,
    @ColumnInfo(name = "start_date")
    val startDate: String,
    @ColumnInfo(name = "end_date")
    val endDate: String,
    val type: String
)

fun List<ProductDatabase>.asDomainModel(): List<Product> {
    return map {
        Product(
            name = it.name,
            image = it.imageUrl,
            newPrice = it.newPrice,
            oldPrice = it.oldPrice,
            shop = it.shop,
            startDate = it.startDate,
            endDate = it.endDate,
            type = it.type
        )
    }
}