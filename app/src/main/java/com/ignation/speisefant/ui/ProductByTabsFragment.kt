package com.ignation.speisefant.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ignation.speisefant.adapter.ProductAdapter
import com.ignation.speisefant.domain.productList
import com.ignation.speisefant.databinding.FragmentProductByTabsBinding

class ProductByTabsFragment : Fragment() {

    private var _binding: FragmentProductByTabsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentProductByTabsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.recyclerView.adapter = ProductAdapter(requireContext(), productList)
        binding.recyclerView.setHasFixedSize(true)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}