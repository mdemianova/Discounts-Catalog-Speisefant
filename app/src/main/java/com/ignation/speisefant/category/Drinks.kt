package com.ignation.speisefant.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.ignation.speisefant.databinding.FragmentProductByTypeBinding
import com.ignation.speisefant.tabs.ProductAdapter
import com.ignation.speisefant.tabs.ProductViewModel
import com.ignation.speisefant.tabs.ProductViewModelFactory

class Drinks : Fragment() {

    private lateinit var binding: FragmentProductByTypeBinding

    private val viewModel: ProductViewModel by activityViewModels() {
        ProductViewModelFactory(requireActivity().application)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductByTypeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.productsByType = viewModel.productsByTypeAndShop("drinks")
        val adapter = ProductAdapter()
        binding.recyclerView.adapter = adapter
        viewModel.productsByType.observe(this.viewLifecycleOwner) {
            it.let {
                adapter.dataset = it
            }
        }
    }
}