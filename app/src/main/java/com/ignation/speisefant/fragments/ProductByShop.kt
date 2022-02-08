package com.ignation.speisefant.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.google.android.material.tabs.TabLayoutMediator
import com.ignation.speisefant.adapters.ViewPagerAdapter
import com.ignation.speisefant.databinding.FragmentProductByCategoryBinding
import com.ignation.speisefant.viewmodel.ProductViewModel
import com.ignation.speisefant.viewmodel.ProductViewModelFactory

class ProductByShop : Fragment() {

    private var _binding: FragmentProductByCategoryBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProductViewModel by activityViewModels() {
        ProductViewModelFactory(requireActivity().application)
    }

    private val navigationArgs: ProductByShopArgs by navArgs()
    private lateinit var category: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentProductByCategoryBinding.inflate(layoutInflater)

        category = navigationArgs.category
        (activity as AppCompatActivity).supportActionBar?.title = category
        viewModel.productByCategory = viewModel.productsByShop(category)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewPager = binding.viewPager
        val adapter = ViewPagerAdapter(requireActivity().supportFragmentManager, lifecycle)
        viewPager.adapter = adapter

        val tabLayout = binding.tabLayout
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Fruits"
                1 -> "Milk"
                2 -> "Drinks"
                else -> "Alcohol"
            }

        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

