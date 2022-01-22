package com.ignation.speisefant.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "database_product")
data class ProductDatabase(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    @ColumnInfo(name = "image_url")
    val imageUrl: String,
    @ColumnInfo(name = "new_price")
    val newPrice: String,
    @ColumnInfo(name = "old_price")
    val oldPrice: String,
    val shop: String,
    val date: String,
    val type: String
)