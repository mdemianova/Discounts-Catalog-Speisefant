package com.ignation.speisefant.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.ignation.speisefant.domain.Product
import com.ignation.speisefant.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
        private val productRepository: ProductRepository
    ) : ViewModel() {

    private val _allActualProducts: LiveData<List<Product>> = productRepository.getActualProducts()
    val allActualProducts: LiveData<List<Product>> = _allActualProducts

    private var _eventNetworkError = MutableLiveData(false)
    val eventNetworkError: LiveData<Boolean> = _eventNetworkError

    val productsOrderByShop = productRepository.getActualProductsOrderedByShop()

    private val shopName = MutableLiveData<String>()

    lateinit var productsByShop: LiveData<List<Product>>

    fun setShop(openedShop: String) {
        shopName.value = openedShop
    }

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

    fun filteredByShop(): LiveData<List<Product>> {
        return Transformations.map(allActualProducts) { list ->
            list.filter { it.shop == shopName.value }
        }
    }

    fun setListForPages() {
        productsByShop = filteredByShop()
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
                Log.d("ProductViewModel", "VM error: ${e.message}")
                _eventNetworkError.value = true
            }
        }
    }
}