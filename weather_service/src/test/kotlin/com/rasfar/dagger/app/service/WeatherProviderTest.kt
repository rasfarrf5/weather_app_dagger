package com.rasfar.dagger.app.service

import com.rasfar.dagger.app.network.NetworkConfiguration
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class WeatherProviderTest {

    @InjectMocks
    private lateinit var subject: WeatherProvider

    @Mock
    private lateinit var weatherService: WeatherService

    @Mock
    private lateinit var weatherDataMapper: WeatherDataMapper

    @Mock
    private lateinit var networkConfiguration: NetworkConfiguration

    @Mock
    private lateinit var response: WeatherResponse

    @Mock
    private lateinit var weatherData: WeatherData

    @Before
    fun setUp() {
        `when`(networkConfiguration.getApiKey()).thenReturn("api_key")

        `when`(weatherService.getWeather("country", "api_key"))
            .thenReturn(Observable.just(response))

        `when`(weatherDataMapper.mapFrom(response))
            .thenReturn(weatherData)
    }

    @Test
    fun getTrendingDetails_verifyServiceCall() {
        subject.getWeatherInfo("country").test()

        verify(weatherService).getWeather("country", "api_key")
    }

    @Test
    fun getTrendingDetails_givenServiceWithResponse_verifyMapperCall() {
        subject.getWeatherInfo("country").test()

        verify(weatherDataMapper).mapFrom(response)
    }

    @Test
    fun getTrendingDetails_givenResponseWithData_returnSuccessResultWithData() {
        val actual = subject.getWeatherInfo("country").test()

        actual.assertValue { result ->
            result is WeatherResult.Success && result.weatherData == weatherData
        }
    }

    @Test
    fun getTrendingDetails_givenErrorResponse_returnFailureResult() {
        `when`(weatherService.getWeather("country", "api_key"))
            .thenReturn(Observable.error(Throwable()))

        val actual = subject.getWeatherInfo("country").test()

        actual.assertValue { result -> result is WeatherResult.Failed }
    }
}