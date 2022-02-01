package com.ignation.speisefant.network

import com.ignation.speisefant.utils.BASE_URL
import com.ignation.speisefant.utils.INPUT_DATA
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface ProductApiService {
    @GET(INPUT_DATA)
    suspend fun getNetworkProducts(): NetworkProductList
}

object ProductApi {
    val retrofitService : ProductApiService by lazy {
        retrofit.create(ProductApiService::class.java)
    }
}