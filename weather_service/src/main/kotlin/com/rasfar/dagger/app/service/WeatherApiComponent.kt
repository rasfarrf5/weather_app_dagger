package com.rasfar.dagger.app.service

import com.rasfar.dagger.app.network.NetworkConfiguration
import dagger.BindsInstance
import dagger.Component
import retrofit2.Retrofit

@Component(modules = [WeatherApiModule::class])
interface WeatherApiComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun retrofit(retrofit: Retrofit): Builder

        @BindsInstance
        fun networkConfiguration(networkConfiguration: NetworkConfiguration): Builder

        fun build(): WeatherApiComponent
    }

    fun weatherProvider(): WeatherProvider
}