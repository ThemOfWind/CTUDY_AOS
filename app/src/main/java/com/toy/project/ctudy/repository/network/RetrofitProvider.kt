package com.toy.project.ctudy.repository.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import java.net.URLDecoder
import java.util.concurrent.TimeUnit

/**
 * Retrofit Provider 정의
 */
interface RetrofitProvider {
    fun createClient(): OkHttpClient
}

class ApplicationInterCeptor : Interceptor {
    private val REQUEST_ACCEPT = "accept"
    private val CONTEXT_TYPE = "Content-Type"
    private val ACCEPT_CONTENT_TYPE = "application/json; charset=UTF-8"

    /**
     * Application -> Okhttp 사이 동작
     * 기본 Interceptor 구현
     */
    override fun intercept(chain: Interceptor.Chain): Response {
        val origin = chain.request()
        val request = origin.newBuilder().apply {
            header(REQUEST_ACCEPT, ACCEPT_CONTENT_TYPE)
            addHeader(CONTEXT_TYPE, ACCEPT_CONTENT_TYPE)
            method(origin.method, origin.body)
        }.build()

        val response = chain.proceed(request)

        return response
    }
}

class NetWorkInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)

        return response
    }
}

class RetrofitProviderImpl : RetrofitProvider {
    private val DEFAULT_TIMEOUT: Long = 15

    override fun createClient() = OkHttpClient.Builder().apply {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        retryOnConnectionFailure(true)
        writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
        readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
        connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
        callTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
        addInterceptor(ApplicationInterCeptor())
        addNetworkInterceptor(interceptor)
    }.build()
}