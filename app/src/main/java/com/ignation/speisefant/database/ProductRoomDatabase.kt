package com.ignation.speisefant.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ProductDatabase::class], version = 10, exportSchema = false)
abstract class ProductRoomDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
}