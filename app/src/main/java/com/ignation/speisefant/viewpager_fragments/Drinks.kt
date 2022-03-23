package com.ignation.speisefant.viewpager_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.ignation.speisefant.adapters.ProductAdapter
import com.ignation.speisefant.databinding.FragmentProductByTypeBinding
import com.ignation.speisefant.viewmodel.ProductViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Drinks : Fragment() {

    private lateinit var binding: FragmentProductByTypeBinding

    private val viewModel: ProductViewModel by activityViewModels()

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

        val adapter = ProductAdapter()
        binding.recyclerView.adapter = adapter
        viewModel.setType("Getränke")
        viewModel.filteredByType(viewModel.productsByShop)
            .observe(this.viewLifecycleOwner) {
                it.let {
                    adapter.dataset = it
                }
                if (adapter.dataset.isEmpty()) {
                    binding.recyclerView.visibility = View.GONE
                    binding.emptyTitle.visibility = View.VISIBLE
                    binding.emptyText.visibility = View.VISIBLE
                } else {
                    binding.recyclerView.visibility = View.VISIBLE
                    binding.emptyTitle.visibility = View.GONE
                    binding.emptyText.visibility = View.GONE
                }
            }
    }
}