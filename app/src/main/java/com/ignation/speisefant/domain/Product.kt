package com.ignation.speisefant.domain

data class Product(
    val name: String,
    val image: String,
    val newPrice: String,
    val oldPrice: String,
    val shop: String,
    val date: String,
    val type: String
)