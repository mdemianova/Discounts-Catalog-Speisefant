package com.ignation.speisefant.domain

data class Product(
    val name: String,
    val image: String,
    val newPrice: Float,
    val oldPrice: Float,
    val shop: String,
    val startDate: String,
    val endDate: String,
    val type: String
)