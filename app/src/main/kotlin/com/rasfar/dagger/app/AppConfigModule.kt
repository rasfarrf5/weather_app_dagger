package com.rasfar.dagger.app

import android.content.Context
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class AppConfigModule {

    @Provides
    fun providesContext(application: WeatherApplication): Context = application.applicationContext

    @Provides
    fun providesCompositeDisposable(): CompositeDisposable = CompositeDisposable()


}