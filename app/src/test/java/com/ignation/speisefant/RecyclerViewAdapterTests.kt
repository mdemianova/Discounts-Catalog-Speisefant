package com.ignation.speisefant

import android.content.Context
import com.ignation.speisefant.adapter.ProductAdapter
import com.ignation.speisefant.domain.Product
import org.junit.Assert.assertEquals
import org.junit.Test

import org.mockito.Mockito.mock

class RecyclerViewAdapterTests {

    private val context = mock(Context::class.java)

    @Test
    fun adapter_size() {
        val data = listOf(
            Product("Tomato", "url1", 2000, 2300,
                "Lidl", "15.02", "28.02", "Food"),
            Product("Chips", "url2", 130, 150,
                "Lidl", "1.02", "14.02", "Food")
        )
        val adapter = ProductAdapter(context, data)
        assertEquals("ProductAdapter is not the correct size", data.size, adapter.itemCount)
    }
}