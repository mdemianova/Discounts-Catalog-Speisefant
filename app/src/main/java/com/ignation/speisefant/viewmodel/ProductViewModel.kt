package com.ignation.speisefant.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.ignation.speisefant.database.ProductRoomDatabase
import com.ignation.speisefant.repository.ProductRepository
import kotlinx.coroutines.launch

const val TAG = "ProductViewModel"

class ProductViewModel(application: Application) : ViewModel() {

    private val productRepository = ProductRepository(ProductRoomDatabase.getDatabase(application))

    val allProducts = productRepository.products

    init {
        refreshDataFromRepository()
    }

    private fun refreshDataFromRepository() {
        viewModelScope.launch {
            try {
                productRepository.refreshProducts()
            } catch (e: Exception) {
                Log.d(TAG, "refreshDataFromRepository: ${e.message}")
            }
        }
    }
}

class ProductViewModelFactory(val app: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ProductViewModel(app) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}