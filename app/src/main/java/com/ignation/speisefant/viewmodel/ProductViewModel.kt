package com.ignation.speisefant.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.ignation.speisefant.domain.Product
import com.ignation.speisefant.repository.DefaultProductRepository
import com.ignation.speisefant.repository.ProductRepository
import kotlinx.coroutines.launch

const val TAG = "ProductViewModel"

class ProductViewModel(private val productRepository: ProductRepository) : ViewModel() {

    private val _allActualProducts: LiveData<List<Product>> = productRepository.actualProducts()
    val allActualProducts: LiveData<List<Product>> = _allActualProducts


    val productsOrderByShop = productRepository.actualProductsOrderedByShop()

    lateinit var productsByShop: LiveData<List<Product>>

    init {
        refreshDataFromRepository()
    }

    fun filteredByType(
        type: String,
        productsDataset: LiveData<List<Product>>
    ): LiveData<List<Product>> {
        return Transformations.map(productsDataset) { list ->
            list.filter { it.type == type }
        }
    }

    fun filteredByShop(shopName: String): LiveData<List<Product>> {
        return Transformations.map(allActualProducts) { list ->
            list.filter { it.shop == shopName }
        }
    }

    fun searchByName(query: String): LiveData<List<Product>> {
        return Transformations.map(allActualProducts) { list ->
            list.filter {
                it.name.contains(query, true)
            }
        }
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

class ProductViewModelFactory(val repository: DefaultProductRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ProductViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}