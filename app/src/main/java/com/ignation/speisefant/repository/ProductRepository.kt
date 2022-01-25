package com.ignation.speisefant.repository

import android.content.Context
import com.ignation.speisefant.R
import com.ignation.speisefant.database.ProductRoomDatabase
import com.ignation.speisefant.network.NetworkProductList
import com.ignation.speisefant.network.asDatabaseModel
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class ProductRepository(context: Context, private val database: ProductRoomDatabase) {

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
    private val type = Types.newParameterizedType(NetworkProductList::class.java)
    private val adapter: JsonAdapter<NetworkProductList> = moshi.adapter(type)
    private val allProducts = context.resources.openRawResource(R.raw.data_input).toString()
    private val result = adapter.fromJson(allProducts)


    suspend fun refreshProducts() {
        database.productDao().insertAllProducts(result!!.asDatabaseModel())
    }



}