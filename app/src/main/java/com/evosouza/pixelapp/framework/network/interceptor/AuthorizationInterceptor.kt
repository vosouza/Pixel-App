package com.evosouza.pixelapp.framework.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptor(private val privateKey: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val newRequest = request.newBuilder()
            .addHeader("Authorization", privateKey)
            .url(request.url)
            .build()
        return chain.proceed(newRequest)
    }
}