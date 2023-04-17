package com.karamalazmeh.sportsmobi.model.network.thesportsdbapi

import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber

class LoggingInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        Timber.d("Interceptor is called")
        val request = chain.request()
        Timber.d(request.url().toString())

        val response = chain.proceed(request)

        return response
    }
}