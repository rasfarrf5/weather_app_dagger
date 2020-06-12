package com.rasfar.dagger.app.main

import com.rasfar.dagger.app.service.WeatherData

class MainContract {

    interface View {

        fun showLoadingSpinner()

        fun hideLoadingSpinner()

        fun showWeatherInfo(weatherData: WeatherData)

        fun showErrorMessage()
    }

    interface Presenter {

        fun setView(view: View)

        fun onViewCreated()

        fun onWeatherContainerClicked()

        fun onViewPaused()
    }
}