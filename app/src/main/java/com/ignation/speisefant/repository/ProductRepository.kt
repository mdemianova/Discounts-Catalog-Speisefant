package com.ignation.speisefant.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.ignation.speisefant.database.ProductRoomDatabase
import com.ignation.speisefant.database.asDomainModel
import com.ignation.speisefant.domain.Product
import com.ignation.speisefant.network.ProductApi
import com.ignation.speisefant.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProductRepository(private val database: ProductRoomDatabase) {

    val products: LiveData<List<Product>> =
        Transformations.map(database.productDao().getAllProducts()) {
            it.asDomainModel()
        }

    suspend fun refreshProducts() {
        withContext(Dispatchers.IO) {
            val networkResponse = ProductApi.retrofitService.getNetworkProducts()
            database.productDao().insertAllProducts(networkResponse.asDatabaseModel())
        }

    }
}