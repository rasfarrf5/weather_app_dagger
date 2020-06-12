package com.rasfar.dagger.app.service

import com.rasfar.dagger.app.network.NetworkConfiguration
import retrofit2.Retrofit

class WeatherApiLibrary(
    private val retrofit: Retrofit,
    private val networkConfiguration: NetworkConfiguration
) {

    private val component = DaggerWeatherApiComponent.builder().apply {
        retrofit(retrofit)
        networkConfiguration(networkConfiguration)
    }.build()

    fun weatherProvider(): WeatherProvider = component.weatherProvider()
}