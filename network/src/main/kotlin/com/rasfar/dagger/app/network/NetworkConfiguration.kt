package com.rasfar.dagger.app.network

import io.reactivex.Scheduler
import java.io.File

interface NetworkConfiguration {

    fun getApiKey(): String

    fun getHost(): String

    fun getCacheDir(): File

    fun getCacheSize(): Long

    fun getTimeoutSeconds(): Long

    fun mainThreadScheduler(): Scheduler

    fun ioScheduler(): Scheduler
}
