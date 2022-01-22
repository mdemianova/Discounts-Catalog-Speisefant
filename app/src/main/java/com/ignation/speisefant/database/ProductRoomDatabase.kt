package com.ignation.speisefant.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ProductDatabase::class], version = 1, exportSchema = false)
abstract class ProductRoomDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
    companion object {
        @Volatile
        private var INSTANCE: ProductRoomDatabase? = null
        fun getDatabase(context: Context): ProductRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ProductRoomDatabase::class.java,
                    "product_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}