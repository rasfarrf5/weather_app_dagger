package com.rasfar.dagger.app

import android.content.Context
import com.rasfar.dagger.app.network.NetworkConfiguration
import com.rasfar.dagger.app.network.NetworkLibrary
import com.rasfar.dagger.app.support.NetworkConfigurationImpl
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Retrofit

@Module
class AppConfigModule {

    @Provides
    fun providesContext(application: WeatherApplication): Context = application.applicationContext

    @Provides
    fun providesNetworkLibrary(context: Context, networkConfiguration: NetworkConfiguration) =
        NetworkLibrary(context, networkConfiguration)

    @Provides
    fun providesNetworkConfiguration(context: Context): NetworkConfiguration =
        NetworkConfigurationImpl(context)

    @Provides
    fun providesRetrofit(
        networkLibrary: NetworkLibrary
    ): Retrofit {
        val okHttpBuilder = networkLibrary.okHttpClient()
            .newBuilder()

        val okHttpClient = okHttpBuilder
            .build()
        return networkLibrary.retrofit().newBuilder().client(okHttpClient).build()
    }

    @Provides
    fun providesCompositeDisposable(): CompositeDisposable = CompositeDisposable()


}