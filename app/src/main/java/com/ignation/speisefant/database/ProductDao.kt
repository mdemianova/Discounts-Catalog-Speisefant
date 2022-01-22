package com.ignation.speisefant.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.ignation.speisefant.domain.Product

@Dao
interface ProductDao {

    @Query("SELECT * FROM database_product")
    fun getAllProducts(): LiveData<List<Product>>

}