package com.ignation.speisefant.repository

import androidx.lifecycle.LiveData
import com.ignation.speisefant.domain.Product

interface ProductRepository {

    fun actualProducts(): LiveData<List<Product>>

    fun actualProductsOrderedByShop(): LiveData<List<Product>>

    suspend fun refreshProducts()
}