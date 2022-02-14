package com.ignation.speisefant.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.ignation.speisefant.database.ProductRoomDatabase
import com.ignation.speisefant.database.asDomainModel
import com.ignation.speisefant.domain.Product
import com.ignation.speisefant.network.ProductApi
import com.ignation.speisefant.network.asDatabaseModel
import com.ignation.speisefant.utils.createActualPeriod
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DefaultProductRepository(private val database: ProductRoomDatabase) : ProductRepository {

    private val actualPeriod = createActualPeriod()

    override suspend fun refreshProducts() {
        withContext(Dispatchers.IO) {
            val networkResponse = ProductApi.retrofitService.getNetworkProducts()
            database.productDao().insertAllProducts(networkResponse.asDatabaseModel())
        }
    }

    override fun actualProducts(): LiveData<List<Product>> {
        return Transformations.map(
            database.productDao().getAllActualProducts(actualPeriod.first, actualPeriod.second)
        ) {
            it.asDomainModel()
        }
    }

    override fun actualProductsOrderedByShop(): LiveData<List<Product>> {
        return Transformations.map(
            database.productDao()
                .getAllProductsOrderedByShop(actualPeriod.first, actualPeriod.second)
        ) {
            it.asDomainModel()
        }
    }
}
