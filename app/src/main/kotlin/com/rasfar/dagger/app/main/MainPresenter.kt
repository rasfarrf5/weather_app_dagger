package com.rasfar.dagger.app.main

import com.rasfar.dagger.app.service.WeatherProvider
import com.rasfar.dagger.app.service.WeatherResult
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MainPresenter @Inject constructor(
    private val weatherProvider: WeatherProvider,
    private val compositeDisposable: CompositeDisposable
) : MainContract.Presenter {

    private lateinit var view: MainContract.View

    override fun setView(view: MainContract.View) {
        this.view = view
    }

    override fun onViewCreated() {
        loadWeatherInfo()
    }

    override fun onWeatherContainerClicked() {
        loadWeatherInfo()
    }

    private fun loadWeatherInfo() {
        val disposable = weatherProvider.getWeatherInfo("Singapore")
            .doOnSubscribe { view.showLoadingSpinner() }
            .doOnTerminate { view.hideLoadingSpinner() }
            .subscribe {
                if (it is WeatherResult.Success) {
                    view.showWeatherInfo(it.weatherData)
                } else {
                    view.showErrorMessage()
                }
            }
        compositeDisposable.add(disposable)
    }

    override fun onViewPaused() {
        compositeDisposable.clear()
    }
}