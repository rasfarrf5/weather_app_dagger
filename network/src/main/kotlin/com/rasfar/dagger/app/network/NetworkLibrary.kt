package com.rasfar.dagger.app.network

import android.content.Context
import okhttp3.OkHttpClient
import retrofit2.Retrofit

class NetworkLibrary constructor(context: Context, configuration: NetworkConfiguration) {

    private var component: NetworkComponent = DaggerNetworkComponent.builder()
            .networkModule(NetworkModule(configuration))
            .build()

    fun retrofit(): Retrofit {
        return component.retrofit()
    }

    fun okHttpClient(): OkHttpClient {
        return component.okHttpClient()
    }
}