package com.ignation.speisefant.tabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.ignation.speisefant.category.Alcohol
import com.ignation.speisefant.category.Drinks
import com.ignation.speisefant.category.FruitsVeggies
import com.ignation.speisefant.category.MilkEggs
import com.ignation.speisefant.databinding.FragmentProductByCategoryBinding

class ProductByCategory : Fragment() {

    private var _binding: FragmentProductByCategoryBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProductViewModel by activityViewModels() {
        ProductViewModelFactory(requireActivity().application)
    }

    private val navigationArgs: ProductByCategoryArgs by navArgs()
    private lateinit var category: String
    private var isShop: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentProductByCategoryBinding.inflate(layoutInflater)

        category = navigationArgs.category
        isShop = navigationArgs.isShop
        (activity as AppCompatActivity).supportActionBar?.title = category
        viewModel.productByCategory = viewModel.productsByShop(category)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewPager = binding.viewPager
        val adapter = ScreenSlidePagerAdapter(requireActivity().supportFragmentManager, lifecycle)
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

    /**
     * A simple pager adapter that represents fragment objects, in sequence.
     */
    private inner class ScreenSlidePagerAdapter(
        fragmentManager: FragmentManager,
        lifecycle: Lifecycle
    ) : FragmentStateAdapter(fragmentManager, lifecycle) {

        var categories = listOf(
            FruitsVeggies(),
            MilkEggs(),
            Drinks(),
            Alcohol()
        )

        override fun getItemCount(): Int = categories.size

        override fun createFragment(position: Int): Fragment = categories[position]
    }
}

