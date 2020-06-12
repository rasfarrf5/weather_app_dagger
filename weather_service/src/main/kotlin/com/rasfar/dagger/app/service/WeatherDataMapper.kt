package com.rasfar.dagger.app.service

import javax.inject.Inject

class WeatherDataMapper @Inject constructor() {

    fun mapFrom(response: WeatherResponse): WeatherData {
        return response.convert()
    }

    private fun WeatherResponse.convert(): WeatherData {
        return WeatherData(
            name = name,
            temperature = main.temp
        )
    }
}