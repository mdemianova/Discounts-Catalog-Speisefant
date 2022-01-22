package com.ignation.speisefant.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity()
data class DatabaseProduct(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val imageUrl: String,
    val newPrice: String,
    val oldPrice: String,
    val shop: String,
    val date: String,
    val type: String
)