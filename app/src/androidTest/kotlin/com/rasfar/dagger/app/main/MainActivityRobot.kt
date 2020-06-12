package com.rasfar.dagger.app.main

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.rasfar.dagger.app.R

class MainActivityRobot {

    fun seesTitle(title: Int): MainActivityRobot {
        onView(withId(R.id.mainTitleText)).check(matches(withText(title)))
        return this
    }

    fun seesCountryName(name: String): MainActivityRobot {
        onView(withId(R.id.mainCountryText)).check(matches(withText(name)))
        return this
    }

    fun seesWeatherTemp(degree: String): MainActivityRobot {
        onView(withId(R.id.mainTemperatureText)).check(matches(withText(degree)))
        return this
    }

    fun seesLoadingSpinner(): MainActivityRobot {
        onView(withId(R.id.mainProgressBar)).check(matches(isDisplayed()))
        return this
    }

    fun seesErrorMessage(errorId: Int): MainActivityRobot {
        onView(withId(R.id.mainCountryText)).check(matches(withText(errorId)))
        return this
    }

    fun clicksOnWeatherContainer(): MainActivityRobot {
        onView(withId(R.id.mainWeatherContainer)).perform(click())
        return this
    }
}