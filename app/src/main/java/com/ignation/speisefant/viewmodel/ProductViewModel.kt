package com.ignation.speisefant.viewmodel

import androidx.lifecycle.*
import com.ignation.speisefant.domain.Product
import com.ignation.speisefant.repository.DefaultProductRepository
import com.ignation.speisefant.repository.ProductRepository
import kotlinx.coroutines.launch

const val TAG = "ProductViewModel"

class ProductViewModel(private val productRepository: ProductRepository) : ViewModel() {

    private val _allActualProducts: LiveData<List<Product>> = productRepository.actualProducts()
    val allActualProducts: LiveData<List<Product>> = _allActualProducts

    private var _eventNetworkError = MutableLiveData(false)
    val eventNetworkError = _eventNetworkError

    val productsOrderByShop = productRepository.actualProductsOrderedByShop()

    lateinit var productsByShop: LiveData<List<Product>>

    fun errorShown() {
        _eventNetworkError.value = false
    }

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

    fun refreshDataFromRepository() {
        viewModelScope.launch {
            _eventNetworkError.value = false
            try {
                productRepository.refreshProducts()
                _eventNetworkError.value = false
            } catch (e: Exception) {
                _eventNetworkError.value = true
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