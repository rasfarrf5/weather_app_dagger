package com.rasfar.dagger.app.network

import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule(private val configuration: NetworkConfiguration) {

    @Provides
    @Singleton
    fun providesOkHttpCache(): Cache {
        return Cache(configuration.getCacheDir(), configuration.getCacheSize())
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(
        cache: Cache,
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .readTimeout(configuration.getTimeoutSeconds(), TimeUnit.SECONDS)
            .connectTimeout(configuration.getTimeoutSeconds(), TimeUnit.SECONDS)
            .cache(cache)

        return builder.build()
    }

    @Provides
    @Singleton
    fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(configuration.getHost())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(
                RxThreadCallAdapter(
                    configuration.ioScheduler(),
                    configuration.mainThreadScheduler()
                )
            )
            .client(okHttpClient)
            .build()
    }
}
