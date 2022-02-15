package com.ignation.speisefant.fragments

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ignation.speisefant.R
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TitleFragmentNavigationTest {

    private lateinit var navController: NavController

    @Before
    fun setup() {
        navController = TestNavHostController(
            ApplicationProvider.getApplicationContext()
        )
        val titleFragmentScenario =
            launchFragmentInContainer<TitleFragment>(themeResId = R.style.Theme_Speisefant_WhiteBar)
        titleFragmentScenario.onFragment { fragment ->
            navController.setGraph(R.navigation.nav_graph)
            Navigation.setViewNavController(fragment.requireView(), navController)
        }
    }

    @Test
    fun click_on_category_gets_in_productByTypeFragment() {
        onView(withId(R.id.recycler_view_category))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click()))

        assertEquals(navController.currentDestination?.id, R.id.productByTypeFragment)
    }

    @Test
    fun click_on_shop_gets_in_productByShopFragment() {
        onView(withId(R.id.recycler_view_shop))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        assertEquals(navController.currentDestination?.id, R.id.productByShopFragment)
    }
}