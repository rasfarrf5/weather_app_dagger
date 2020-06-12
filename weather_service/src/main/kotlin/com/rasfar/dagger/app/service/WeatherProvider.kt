package com.rasfar.dagger.app.service

import com.rasfar.dagger.app.network.NetworkConfiguration
import io.reactivex.Observable
import javax.inject.Inject

class WeatherProvider @Inject constructor(
    private val weatherService: WeatherService,
    private val weatherDataMapper: WeatherDataMapper,
    private val networkConfiguration: NetworkConfiguration
) {

    fun getWeatherInfo(country: String): Observable<WeatherResult> {
        return weatherService.getWeather(country, networkConfiguration.getApiKey())
            .map { response ->
                val data = weatherDataMapper.mapFrom(response)
                WeatherResult.Success(data) as WeatherResult
            }
            .onErrorReturn { WeatherResult.Failed }
    }
}