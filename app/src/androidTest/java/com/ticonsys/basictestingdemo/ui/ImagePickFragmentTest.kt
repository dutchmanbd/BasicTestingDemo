package com.ticonsys.basictestingdemo.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.MediumTest
import com.ticonsys.basictestingdemo.R
import com.ticonsys.basictestingdemo.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.android.synthetic.main.fragment_add_shopping_item.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*


@MediumTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class ImagePickFragmentTest{

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setup(){
        hiltRule.inject()
    }

    @Test
    fun pressBackButton_popBackStack(){
        val navController = mock(NavController::class.java)

        launchFragmentInHiltContainer<AddShoppingItemFragment> {
            Navigation.setViewNavController(requireView(), navController)
        }
        onView(withId(R.id.ivShoppingImage)).perform(click())
        pressBack()
        verify(navController).popBackStack()
    }


}