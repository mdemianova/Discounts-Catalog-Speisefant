package com.ignation.speisefant.repository

import android.app.Application
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

class DefaultProductRepository(val application: Application) : ProductRepository {

    private val actualPeriod = createActualPeriod()
    private val database = ProductRoomDatabase.getDatabase(application)

    /**
     * Fetches data from the server and caches it in Room database.
     */
    override suspend fun refreshProducts() {
        withContext(Dispatchers.IO) {
            val networkResponse = ProductApi.retrofitService.getNetworkProducts()
            database.productDao().insertAllProducts(networkResponse.asDatabaseModel())
        }
    }

    override fun getActualProducts(): LiveData<List<Product>> {
        return Transformations.map(
            database.productDao().getAllActualProducts(actualPeriod.first, actualPeriod.second)
        ) {
            it.asDomainModel()
        }
    }

    override fun getActualProductsOrderedByShop(): LiveData<List<Product>> {
        return Transformations.map(
            database.productDao()
                .getAllProductsOrderedByShop(actualPeriod.first, actualPeriod.second)
        ) {
            it.asDomainModel()
        }
    }
}
