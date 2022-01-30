package com.ignation.speisefant.repository

import android.content.Context
import com.ignation.speisefant.R
import com.ignation.speisefant.database.ProductRoomDatabase
import com.ignation.speisefant.network.NetworkProduct
import com.ignation.speisefant.network.asDatabaseModel
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.io.BufferedReader

class ProductRepository(context: Context, private val database: ProductRoomDatabase) {

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val type = Types.newParameterizedType(List::class.java, NetworkProduct::class.java)

    private val adapter: JsonAdapter<List<NetworkProduct>> = moshi.adapter(type)
    private val input = context.resources.openRawResource(R.raw.data_input)

    private fun readFile(): String {
        val reader = BufferedReader(input.reader())
        var allProducts: String
        try {
            allProducts = reader.readText()
        } finally {
            reader.close()
        }
        return allProducts
    }

    private val result = adapter.fromJson(readFile())

    suspend fun refreshProducts() {
        database.productDao().insertAllProducts(result!!.asDatabaseModel())
    }
}