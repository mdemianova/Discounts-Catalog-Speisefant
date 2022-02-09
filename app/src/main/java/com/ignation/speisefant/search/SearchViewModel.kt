package com.ignation.speisefant.search

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ignation.speisefant.database.ProductDatabase
import com.ignation.speisefant.database.ProductRoomDatabase
import com.ignation.speisefant.database.asDomainModel
import com.ignation.speisefant.domain.Product

class SearchViewModel(application: Application) : ViewModel() {

    val application: Application = application

    fun search(query: String): LiveData<List<Product>> {
        val dataset: LiveData<List<ProductDatabase>> =
            ProductRoomDatabase.getDatabase(application).productDao().searchProducts(query)
        return Transformations.map(dataset) {
            it.asDomainModel()
        }
    }
}

class SearchViewModelFactory(val app: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SearchViewModel(app) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}