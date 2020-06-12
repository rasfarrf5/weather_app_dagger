package com.rasfar.dagger.app

import com.rasfar.dagger.app.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityInjector {

    @ContributesAndroidInjector
    abstract fun contributesMainActivity(): MainActivity
}