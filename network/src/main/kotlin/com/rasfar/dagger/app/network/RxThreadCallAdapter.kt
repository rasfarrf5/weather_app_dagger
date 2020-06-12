package com.rasfar.dagger.app.network

import io.reactivex.Observable
import io.reactivex.Scheduler
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.lang.reflect.Type

@Suppress("UNCHECKED_CAST")
class RxThreadCallAdapter(
    private val subscribeScheduler: Scheduler,
    private val observerScheduler: Scheduler
) : CallAdapter.Factory() {

    internal var rxFactory = RxJava2CallAdapterFactory.create()

    override fun get(
        returnType: Type, annotations: Array<Annotation>, retrofit: Retrofit
    ): ThreadCallAdapter<Any>? {
        val callAdapter = rxFactory.get(returnType, annotations, retrofit)
        return if (callAdapter != null) ThreadCallAdapter(callAdapter as CallAdapter<Any, Observable<*>>) else null
    }

    inner class ThreadCallAdapter<R>(var delegateAdapter: CallAdapter<R, Observable<*>>) :
        CallAdapter<R, Observable<*>> {

        override fun responseType(): Type {
            return delegateAdapter.responseType()
        }

        override fun adapt(call: Call<R>): Observable<*> {
            return delegateAdapter.adapt(call)
                .subscribeOn(subscribeScheduler)
                .observeOn(observerScheduler)
        }
    }
}
