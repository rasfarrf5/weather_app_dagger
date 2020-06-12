package com.rasfar.dagger.app

import com.rasfar.dagger.app.main.MainActivity
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ActivityInjector::class,

        AppConfigModule::class
    ]
)
interface ApplicationComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(weatherApplication: WeatherApplication): Builder

        fun build(): ApplicationComponent
    }

    fun inject(weatherApplication: WeatherApplication)

    fun inject(mainActivity: MainActivity)
}
