package com.rasfar.dagger.app.main

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.rasfar.dagger.app.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java, false, false)

    private val mainActivityRobot = MainActivityRobot()

    @Test
    fun onLaunchMainScreen_seesTitle() {
        activityRule.launchActivity(null)

        mainActivityRobot
            .seesTitle(R.string.app_name)
    }
}