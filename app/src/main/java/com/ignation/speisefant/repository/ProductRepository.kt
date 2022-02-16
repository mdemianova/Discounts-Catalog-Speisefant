package com.ignation.speisefant.repository

import androidx.lifecycle.LiveData
import com.ignation.speisefant.domain.Product

interface ProductRepository {

    fun getActualProducts(): LiveData<List<Product>>

    fun getActualProductsOrderedByShop(): LiveData<List<Product>>

    suspend fun refreshProducts()
}