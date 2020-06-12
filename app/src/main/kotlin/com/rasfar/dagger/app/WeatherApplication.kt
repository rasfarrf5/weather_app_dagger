package com.rasfar.dagger.app

import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class WeatherApplication : Application(), HasAndroidInjector {

    private val appComponent = DaggerApplicationComponent.builder().apply {
        application(this@WeatherApplication)
    }.build()

    override fun onCreate() {
        super.onCreate()

        appComponent.inject(this)
    }

    @Inject
    lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = dispatchingActivityInjector
}