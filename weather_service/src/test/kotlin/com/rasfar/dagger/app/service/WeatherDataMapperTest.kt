package com.rasfar.dagger.app.service

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class WeatherDataMapperTest {

    @InjectMocks
    private lateinit var subject: WeatherDataMapper

    @Test
    fun mapFrom_givenResponse_returnsWeatherData() {
        val actual = subject.mapFrom(getResponse())

        assertThat(actual.name).isEqualTo("name")
        assertThat(actual.temperature).isEqualTo(100.00)
    }

    private fun getResponse() = WeatherResponse(
        "name",
        WeatherResponse.Main(100.00)
    )
}