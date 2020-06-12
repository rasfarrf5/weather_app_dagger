package com.rasfar.dagger.app.network

import dagger.Component
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface NetworkComponent {

    fun retrofit(): Retrofit

    fun okHttpClient(): OkHttpClient
}