package com.ignation.speisefant.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.ignation.speisefant.database.ProductRoomDatabase
import com.ignation.speisefant.database.asDomainModel
import com.ignation.speisefant.domain.Product
import com.ignation.speisefant.network.NetworkProduct
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class ProductRepository(context: Context, private val database: ProductRoomDatabase) {


    val products: LiveData<List<Product>> =
        Transformations.map(database.productDao().getAllProducts()) {
            it.asDomainModel()
        }

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val type = Types.newParameterizedType(List::class.java, NetworkProduct::class.java)

    private val adapter: JsonAdapter<List<NetworkProduct>> = moshi.adapter(type)
//    private val input = context.resources.openRawResource(R.raw.data_input)
//
//    private fun readFile(): String {
//        val reader = BufferedReader(input.reader())
//        val allProducts: String
//        try {
//            allProducts = reader.readText()
//        } finally {
//            reader.close()
//        }
//        return allProducts
//    }

    //private val result = adapter.fromJson(readFile())
//
//    suspend fun refreshProducts() {
//        database.productDao().insertAllProducts(result!!.asDatabaseModel())
//    }
}