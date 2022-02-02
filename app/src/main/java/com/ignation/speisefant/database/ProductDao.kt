package com.ignation.speisefant.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ProductDao {

    @Query("SELECT * FROM database_product")
    fun getAllProducts(): LiveData<List<ProductDatabase>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllProducts(products: List<ProductDatabase>)

    @Query("SELECT * FROM database_product WHERE start_date BETWEEN :start AND :end AND type = :type GROUP BY shop")
    fun getProductsByType(start: String, end: String, type: String): LiveData<List<ProductDatabase>>

    @Query("SELECT * FROM database_product WHERE start_date BETWEEN :start AND shop = :shop AND type = :type")
    fun getProductsForShopByType(start: String, end: String, shop: String, type: String): LiveData<List<ProductDatabase>>
}