package com.rasfar.dagger.app.service

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class WeatherApiModule {

    @Provides
    fun providesWeatherService(retrofit: Retrofit): WeatherService =
        retrofit.create(WeatherService::class.java)
}