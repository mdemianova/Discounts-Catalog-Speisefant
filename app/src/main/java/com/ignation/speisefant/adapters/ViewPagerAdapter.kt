package com.ignation.speisefant.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ignation.speisefant.viewpager_fragments.*

/**
 * A simple pager adapter that represents fragment objects, in sequence.
 */
class ViewPagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    private var categories = listOf(
        MilkEggs(),
        Meat(),
        Bread(),
        Sausage(),
        Grocery(),
        Frozen(),
        Sweets(),
        Ready(),
        Coffee(),
        Drinks(),
        Alcohol(),
        Hygiene(),
        Home()
    )

    override fun getItemCount(): Int = categories.size

    override fun createFragment(position: Int): Fragment = categories[position]
}