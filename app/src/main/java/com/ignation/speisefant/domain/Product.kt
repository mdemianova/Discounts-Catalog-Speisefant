package com.ignation.speisefant.domain

data class Product(
    val name: String,
    val image: String,
    val newPrice: Int,
    val oldPrice: Int,
    val shop: String,
    val startDate: Long,
    val endDate: Long,
    val type: String
)