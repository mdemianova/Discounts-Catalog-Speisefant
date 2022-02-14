package com.ignation.speisefant.viewmodel

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ignation.speisefant.repository.FakeProductRepository
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

//@RunWith(AndroidJUnit4::class)
class ProductViewModelTest {

    private lateinit var productViewModel: ProductViewModel
    private lateinit var fakeRepository: FakeProductRepository


    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        val application: Application = Mockito.mock(Application::class.java)
        productViewModel = ProductViewModel(application)
        fakeRepository = FakeProductRepository()
    }

    @Test
    fun datasource_filter_by_type_returns_2() {
        val filtered = productViewModel.filterByType("drinks", fakeRepository.actualProducts())
        assertEquals("Filter by type gave wrong result", 2, filtered.value?.size)
    }
}