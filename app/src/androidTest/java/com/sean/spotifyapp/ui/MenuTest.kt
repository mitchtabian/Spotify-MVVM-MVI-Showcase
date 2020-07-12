package com.sean.spotifyapp.ui

import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import com.sean.spotifyapp.R
import com.sean.spotifyapp.TestBaseApplication
import com.sean.spotifyapp.activity.MainActivity
import com.sean.spotifyapp.di.TestAppComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.InternalCoroutinesApi
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@InternalCoroutinesApi
@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4ClassRunner::class)
class MenuTest {

    @FlowPreview
    @Test
    fun basicNavigationTest() {

//        val app = InstrumentationRegistry
//            .getInstrumentation()
//            .targetContext
//            .applicationContext as TestBaseApplication

//        (app.appComponent as TestAppComponent)
//            .inject(this)

//
//        val scenario = launchActivity<MainActivity>()
//
//        val button = Espresso.onView(withId(R.id.Button_menu_browse_categories))
//
//        button.check(ViewAssertions.matches(ViewMatchers.isDisplayed()))


    }
}