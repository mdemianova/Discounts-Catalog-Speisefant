package com.ignation.speisefant.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "database_product")
data class ProductDatabase(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = null,
    val name: String,
    @ColumnInfo(name = "image_url")
    val imageUrl: String,
    @ColumnInfo(name = "new_price")
    val newPrice: Float,
    @ColumnInfo(name = "old_price")
    val oldPrice: Float,
    val shop: String,
    @ColumnInfo(name = "start_date")
    val startDate: String,
    @ColumnInfo(name = "end_date")
    val endDate: String,
    val type: String
)