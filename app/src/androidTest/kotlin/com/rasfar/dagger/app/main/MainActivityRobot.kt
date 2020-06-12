package com.rasfar.dagger.app.main

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.rasfar.dagger.app.R

class MainActivityRobot {

    fun seesTitle(title: Int): MainActivityRobot {
         onView(withId(R.id.mainTitleText)).check(matches(withText(title)))
        return this
    }
}