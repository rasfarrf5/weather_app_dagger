package com.rasfar.dagger.app.main

import com.rasfar.dagger.app.service.WeatherData
import com.rasfar.dagger.app.service.WeatherProvider
import com.rasfar.dagger.app.service.WeatherResult
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainPresenterTest {

    @InjectMocks
    private lateinit var subject: MainPresenter

    @Mock
    private lateinit var weatherProvider: WeatherProvider
    @Mock
    private lateinit var compositeDisposable: CompositeDisposable

    @Mock
    private lateinit var view: MainContract.View
    @Mock
    private lateinit var weatherData: WeatherData
    @Mock
    private lateinit var weatherResultSuccess: WeatherResult.Success
    @Mock
    private lateinit var weatherResultFailed: WeatherResult.Failed

    @Before
    fun setUp() {
        subject.setView(view)

        `when`(weatherResultSuccess.weatherData).thenReturn(weatherData)

        `when`(weatherProvider.getWeatherInfo("Singapore"))
            .thenReturn(Observable.just(weatherResultSuccess))
    }

    @Test
    fun onViewCreated_verifyProvider() {
        subject.onViewCreated()

        verify(weatherProvider).getWeatherInfo("Singapore")
    }

    @Test
    fun onViewCreated_givenProviderSuccess_showsWeatherInfo() {
        subject.onViewCreated()

        val inOrder = inOrder(view)
        inOrder.verify(view).showLoadingSpinner()
        inOrder.verify(view).showWeatherInfo(weatherData)
        inOrder.verify(view).hideLoadingSpinner()
    }

    @Test
    fun onViewCreated_givenProviderFailed_showsErrorMessage() {
        `when`(weatherProvider.getWeatherInfo("Singapore"))
            .thenReturn(Observable.just(weatherResultFailed))

        subject.onViewCreated()

        val inOrder = inOrder(view)
        inOrder.verify(view).showLoadingSpinner()
        inOrder.verify(view).showErrorMessage()
        inOrder.verify(view).hideLoadingSpinner()
    }

    @Test
    fun onWeatherContainerClicked_verifyProvider() {
        subject.onWeatherContainerClicked()

        verify(weatherProvider).getWeatherInfo("Singapore")
    }

    @Test
    fun onWeatherContainerClicked_givenProviderSuccess_showsWeatherInfo() {
        subject.onWeatherContainerClicked()

        val inOrder = inOrder(view)
        inOrder.verify(view).showLoadingSpinner()
        inOrder.verify(view).showWeatherInfo(weatherData)
        inOrder.verify(view).hideLoadingSpinner()
    }

    @Test
    fun onWeatherContainerClicked_givenProviderFailed_showsErrorMessage() {
        `when`(weatherProvider.getWeatherInfo("Singapore"))
            .thenReturn(Observable.just(weatherResultFailed))

        subject.onWeatherContainerClicked()

        val inOrder = inOrder(view)
        inOrder.verify(view).showLoadingSpinner()
        inOrder.verify(view).showErrorMessage()
        inOrder.verify(view).hideLoadingSpinner()
    }

    @Test
    fun onViewPaused_verifyClearDisposables() {
        subject.onViewPaused()

        verify(compositeDisposable).clear()
    }

}