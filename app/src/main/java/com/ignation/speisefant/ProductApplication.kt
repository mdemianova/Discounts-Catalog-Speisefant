package com.ignation.speisefant

import android.app.Application
import com.ignation.speisefant.database.ProductRoomDatabase

class ProductApplication : Application() {
    val database: ProductRoomDatabase by lazy { ProductRoomDatabase.getDatabase(this) }
}