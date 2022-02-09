package com.ignation.speisefant.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ProductDao {

    @Query("SELECT * FROM database_product WHERE start_date BETWEEN :start AND :end")
    fun getAllActualProducts(start: Long, end: Long): LiveData<List<ProductDatabase>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllProducts(products: List<ProductDatabase>)

    @Query("SELECT * FROM database_product WHERE start_date BETWEEN :start AND :end ORDER BY shop")
    fun getAllProductsOrderedByShop(start: Long, end: Long): LiveData<List<ProductDatabase>>

    @Query("SELECT * FROM database_product WHERE start_date BETWEEN :start AND :end AND shop = :shop")
    fun getProductsByShop(start: String, end: String, shop: String): LiveData<List<ProductDatabase>>

    @Query("SELECT * FROM database_product WHERE name LIKE :query")
    fun searchProducts(query: String): LiveData<List<ProductDatabase>>
}