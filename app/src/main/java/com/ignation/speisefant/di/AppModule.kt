package com.ignation.speisefant.di

import android.content.Context
import androidx.room.Room
import com.ignation.speisefant.database.ProductRoomDatabase
import com.ignation.speisefant.repository.DefaultProductRepository
import com.ignation.speisefant.repository.ProductRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
abstract class RepoModule {

    companion object {

        @Singleton
        @Provides
        fun provideProductRoomDatabase(
            @ApplicationContext app: Context
        ) = Room.databaseBuilder(
            app,
            ProductRoomDatabase::class.java,
            "product_database"
        )
            .fallbackToDestructiveMigration()
            .build()

        @Singleton
        @Provides
        fun provideProductDao(db: ProductRoomDatabase) = db.productDao()
    }

    @Binds
    abstract fun provideDefaultRepo(repo: DefaultProductRepository): ProductRepository
}