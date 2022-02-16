package com.ignation.speisefant.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.ignation.speisefant.domain.Product

class FakeProductRepository : ProductRepository {

    private val dataset = listOf(
        Product(
            name = "Orange Juice",
            image = "juice.jpg",
            newPrice = 1100,
            oldPrice = 1556,
            shop = "Rewe",
            startDate = 1644188400000,
            endDate = 1645225200000,
            type = "drinks"
        ),
        Product(
            name = "Cola",
            image = "cola.jpg",
            newPrice = 2070,
            oldPrice = 2300,
            shop = "Rewe",
            startDate = 1644188400000,
            endDate = 1645225200000,
            type = "drinks"
        ),
        Product(
            name = "Cheese Mozzarella",
            image = "cheese.jpg",
            newPrice = 357,
            oldPrice = 400,
            shop = "Lidl",
            startDate = 1644188400000,
            endDate = 1645225200000,
            type = "milk"
        ),
        Product(
            name = "Ritter Sport",
            image = "ritter.jpg",
            newPrice = 235,
            oldPrice = 500,
            shop = "Lidl",
            startDate = 1644188400000,
            endDate = 1645225200000,
            type = "sweets"
        ),
        Product(
            name = "Tomato",
            image = "tomato.jpg",
            newPrice = 75,
            oldPrice = 100,
            shop = "Rewe",
            startDate = 1644188400000,
            endDate = 1645225200000,
            type = "fruits"
        ),
        Product(
            name = "Apple",
            image = "apple.jpg",
            newPrice = 105,
            oldPrice = 130,
            shop = "hey",
            startDate = 1644188400000,
            endDate = 1645225200000,
            type = "fruits"
        )

    )

    private val observableProducts = MutableLiveData<List<Product>>()

    override fun getActualProducts(): LiveData<List<Product>> {
        return observableProducts
    }

    override fun getActualProductsOrderedByShop(): LiveData<List<Product>> {
        return Transformations.map(observableProducts) { list ->
            list.sortedBy { it.shop }
        }
    }

    override suspend fun refreshProducts() {
        observableProducts.value = dataset
    }
}