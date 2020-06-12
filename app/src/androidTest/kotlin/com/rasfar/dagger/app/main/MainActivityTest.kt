package com.rasfar.dagger.app.main

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.rasfar.dagger.app.R
import com.rasfar.dagger.app.support.MockServerRobot
import com.rasfar.dagger.app.support.MockWebServerRule
import com.rasfar.dagger.app.support.UiAction
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java, false, false)

    @get:Rule
    val mockWebServerRule = MockWebServerRule()

    private val mockServerRobot = MockServerRobot(mockWebServerRule)
    private val mainActivityRobot = MainActivityRobot()

    @Test
    fun onLaunchMainScreen_onWeatherLoadSuccess_seesTitleWithWeatherInfo() {
        activityRule.launchActivity(null)

        mockServerRobot.performUiAction(object : UiAction {
            override fun perform() {
                mainActivityRobot.seesLoadingSpinner()
            }
        }).enqueueResponse("weather/get.json")

        mainActivityRobot
            .seesTitle(R.string.app_name)
            .seesCountryName("Singapore")
            .seesWeatherTemp("300.86")
    }

    @Test
    fun onLaunchMainScreen_onWeatherLoadFailed_seesErrorMessage() {
        activityRule.launchActivity(null)

        mockServerRobot.performUiAction(object : UiAction {
            override fun perform() {
                mainActivityRobot.seesLoadingSpinner()
            }
        }).enqueueErrorResponse()

        mainActivityRobot
            .seesTitle(R.string.app_name)
            .seesErrorMessage(R.string.failed_message)
    }

    @Test
    fun onLaunchMainScreen_onLoadErrorClicksWeatherContainer_seesRefreshWeatherInfo() {
        activityRule.launchActivity(null)

        mockServerRobot.performUiAction(object : UiAction {
            override fun perform() {
                mainActivityRobot.seesLoadingSpinner()
            }
        }).enqueueErrorResponse()

        mainActivityRobot
            .seesErrorMessage(R.string.failed_message)

        mockServerRobot.performUiAction(object : UiAction {
            override fun perform() {
                mainActivityRobot.clicksOnWeatherContainer()

                mainActivityRobot.seesLoadingSpinner()
            }
        }).enqueueResponse("weather/get2.json")

        mainActivityRobot
            .seesCountryName("Singapore2")
            .seesWeatherTemp("200.06")
    }
}