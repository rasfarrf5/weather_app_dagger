package com.rasfar.dagger.app.main

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import com.rasfar.dagger.app.R
import com.rasfar.dagger.app.service.WeatherData
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainContract.View {

    @Inject
    lateinit var presenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.setView(this)

        mainWeatherContainer.setOnClickListener {
            presenter.onWeatherContainerClicked()
        }

        presenter.onViewCreated()
    }

    override fun showLoadingSpinner() {
        mainProgressBar.visibility = VISIBLE
    }

    override fun hideLoadingSpinner() {
        mainProgressBar.visibility = GONE
    }

    override fun showWeatherInfo(weatherData: WeatherData) {
        mainCountryText.text = weatherData.name
        mainTemperatureText.text = "${weatherData.temperature}"

        mainTemperatureText.visibility = VISIBLE
        mainTemperatureText.visibility = VISIBLE
    }

    override fun showErrorMessage() {
        mainCountryText.text = getString(R.string.failed_message)
        mainTemperatureText.visibility = GONE
    }

    override fun onPause() {
        super.onPause()

        presenter.onViewPaused()
    }
}