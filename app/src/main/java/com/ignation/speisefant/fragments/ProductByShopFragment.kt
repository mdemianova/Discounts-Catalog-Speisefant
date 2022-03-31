package com.ignation.speisefant.fragments

import android.os.Bundle
import android.util.Log
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
import com.ignation.speisefant.utils.*
import com.ignation.speisefant.viewmodel.ProductViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * This fragment filters a list of all products by a chosen shop. It holds ViewPager with Tabs.
 */
@AndroidEntryPoint
class ProductByShopFragment : Fragment() {

    private var _binding: FragmentProductByShopBinding? = null
    private val binding get() = _binding!!

    private val productViewModel: ProductViewModel by activityViewModels()

    private val navigationArgs: ProductByShopFragmentArgs by navArgs()
    private lateinit var shopName: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        shopName = navigationArgs.shop
        (activity as AppCompatActivity).supportActionBar?.title = shopName

        _binding = FragmentProductByShopBinding.inflate(layoutInflater)

        productViewModel.setShop(shopName)
        productViewModel.setListForPages()
        Log.d("ProductByShopF", "ViewModel: ${productViewModel.hashCode()}")

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
                0 -> MILK
                1 -> MEAT
                2 -> BAKERY
                3 -> COLD_CUTS
                4 -> GROCERY
                5 -> FROZEN
                6 -> SNACKS
                7 -> READY_FOOD
                8 -> COFFEE_TEA
                9 -> DRINKS
                10 -> ALCOHOL
                11 -> HYGIENE
                else -> HOME
            }
        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

