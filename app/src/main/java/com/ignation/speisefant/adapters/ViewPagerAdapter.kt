package com.ignation.speisefant.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ignation.speisefant.viewpager_fragments.Alcohol
import com.ignation.speisefant.viewpager_fragments.Drinks
import com.ignation.speisefant.viewpager_fragments.FruitsVeggies
import com.ignation.speisefant.viewpager_fragments.MilkEggs

/**
 * A simple pager adapter that represents fragment objects, in sequence.
 */
class ViewPagerAdapter(
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