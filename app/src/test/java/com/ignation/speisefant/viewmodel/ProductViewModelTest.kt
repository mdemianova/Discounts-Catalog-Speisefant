package com.ignation.speisefant.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ignation.speisefant.MainCoroutineRule
import com.ignation.speisefant.repository.FakeProductRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ProductViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var productViewModel: ProductViewModel

    @Before
    fun setup() {
        productViewModel = ProductViewModel(FakeProductRepository())
    }

    /**
     * ProductViewModel functions use Transformations.map(). To trigger a transformation, an object
     * has to be observed for changes.
     */
    @Test
    fun dataset_filter_by_type_drinks_returns_2() {
        val dataset = productViewModel.allActualProducts
        productViewModel.setType("drinks")
        val filtered = productViewModel.filteredByType(dataset)
        filtered.observeForever {}
        assertEquals("Filter by type gave wrong result", 2, filtered.value?.size)
    }

    @Test
    fun dataset_filter_by_shop_rewe_returns_3() {
        val filtered = productViewModel.filteredByShop()
        filtered.observeForever {}
        assertEquals("Filter by shop gave wrong result", 3, filtered.value?.size)
    }

    @Test
    fun search_by_name_apple_returns_1() {
        val filtered = productViewModel.searchByName("apple")
        filtered.observeForever {}
        assertEquals("Search by name gave wrong result", 1, filtered.value?.size)
    }
}