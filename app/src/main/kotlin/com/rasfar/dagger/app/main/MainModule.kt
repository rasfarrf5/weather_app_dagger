package com.rasfar.dagger.app.main

import com.rasfar.dagger.app.network.NetworkConfiguration
import com.rasfar.dagger.app.service.WeatherApiLibrary
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
abstract class MainModule {

    @Binds
    abstract fun providesMainPresenter(mainPresenter: MainPresenter): MainContract.Presenter

    companion object {

        @Provides
        @JvmStatic
        fun providesWeatherProvider(library: WeatherApiLibrary) = library.weatherProvider()

        @Provides
        @JvmStatic
        fun providesWeatherLibrary(retrofit: Retrofit, configuration: NetworkConfiguration) =
            WeatherApiLibrary(retrofit, configuration)
    }
}