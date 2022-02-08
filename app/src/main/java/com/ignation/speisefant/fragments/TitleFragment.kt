package com.ignation.speisefant.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ignation.speisefant.adapters.CategoryAdapter
import com.ignation.speisefant.adapters.ShopAdapter
import com.ignation.speisefant.databinding.FragmentTitleBinding

class TitleFragment : Fragment() {

    private var _binding: FragmentTitleBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTitleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val categoryAdapter = CategoryAdapter {
            val action = TitleFragmentDirections.actionTitleFragmentToProductByCategoryFragment(it.title)
            this.findNavController().navigate(action)
        }
        binding.recyclerViewCategory.adapter = categoryAdapter


        val shopAdapter = ShopAdapter {
            val action = TitleFragmentDirections.actionTitleFragmentToProductByShopFragment(it.title)
            this.findNavController().navigate(action)
        }
        binding.recyclerViewShop.adapter = shopAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}