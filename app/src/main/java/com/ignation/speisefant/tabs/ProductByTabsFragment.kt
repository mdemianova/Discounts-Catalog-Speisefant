package com.ignation.speisefant.tabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ignation.speisefant.databinding.FragmentProductByTabsBinding
import com.ignation.speisefant.viewmodel.ProductViewModel
import com.ignation.speisefant.viewmodel.ProductViewModelFactory

class ProductByTabsFragment : Fragment() {

    private var _binding: FragmentProductByTabsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProductViewModel by lazy {
        val activity = requireNotNull(this.activity)
        ViewModelProvider(this, ProductViewModelFactory(activity.application))
            .get(ProductViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentProductByTabsBinding.inflate(layoutInflater)

        val adapter = ProductAdapter()
        binding.recyclerView.adapter = adapter

        viewModel.allProducts.observe(this.viewLifecycleOwner) {
            it?.let {
                adapter.dataset = it
            }
        }

        binding.recyclerView.setHasFixedSize(true)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}