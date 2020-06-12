package com.rasfar.dagger.app.service

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("weather")
    fun getWeather(
        @Query("q") country: String,
        @Query("appid") apiKey: String
    ): Observable<WeatherResponse>
}