package com.ignation.speisefant.search

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import com.ignation.speisefant.R
import com.ignation.speisefant.databinding.FragmentProductByTypeBinding

private const val TAG = "SearchableActivity"

class SearchableActivity : AppCompatActivity() {

    private lateinit var binding: FragmentProductByTypeBinding

    private val viewModel: SearchViewModel by viewModels {
        SearchViewModelFactory(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.fragment_product_by_type)
        handleIntent(intent)
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        handleIntent(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.search, menu)

        val searchView = menu.findItem(R.id.action_search).actionView as SearchView
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager

        searchView.apply {
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
        }

        return true
    }

    private fun doMySearch(query: String) {
        Log.d(TAG, "doMySearch: search performed")
        val result = viewModel.search(query)
        Toast.makeText(this, "I got: ${result.value?.size}", Toast.LENGTH_SHORT).show()

    }

    private fun handleIntent(intent: Intent) {
        Log.d(TAG, "handleIntent: got intent")
        if (Intent.ACTION_SEARCH == intent.action) {
            intent.getStringExtra(SearchManager.QUERY)?.also { query ->
                doMySearch(query)
            }
        }
    }
}