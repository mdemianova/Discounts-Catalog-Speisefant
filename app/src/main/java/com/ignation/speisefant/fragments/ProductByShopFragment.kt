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
import com.ignation.speisefant.databinding.FragmentProductByShopBinding
import com.ignation.speisefant.repository.DefaultProductRepository
import com.ignation.speisefant.viewmodel.ProductViewModel
import com.ignation.speisefant.viewmodel.ProductViewModelFactory

class ProductByShopFragment : Fragment() {

    private var _binding: FragmentProductByShopBinding? = null
    private val binding get() = _binding!!

    private val productViewModel: ProductViewModel by activityViewModels() {
        ProductViewModelFactory(DefaultProductRepository(requireActivity().application))
    }

    private val navigationArgs: ProductByShopFragmentArgs by navArgs()
    private lateinit var shopName: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        shopName = navigationArgs.shop
        (activity as AppCompatActivity).supportActionBar?.title = shopName

        _binding = FragmentProductByShopBinding.inflate(layoutInflater)

        productViewModel.productsByShop = productViewModel.filteredByShop(shopName)

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

