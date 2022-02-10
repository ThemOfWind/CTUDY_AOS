package com.toy.project.ctudy.repository.network

import android.util.Log
import com.toy.project.ctudy.repository.pref.UserPref
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

class ApplicationInterCeptor(userPref: UserPref) : Interceptor {
    private val REQUEST_ACCEPT = "accept"
    private val CONTEXT_TYPE = "Content-Type"
    private val ACCEPT_CONTENT_TYPE = "application/json; charset=UTF-8"
    private val AUTHORIZATION = "Authorization"
    private val loginState = userPref.getLoginStatus()
    private val authorizationToken = userPref.getAuthorizationToken()

    /**
     * Application -> Okhttp 사이 동작
     * 기본 Interceptor 구현
     */
    override fun intercept(chain: Interceptor.Chain): Response {
        val origin = chain.request()
        val request = origin.newBuilder().apply {
            header(REQUEST_ACCEPT, ACCEPT_CONTENT_TYPE)
            // 로그인 상태일 경우 헤더에 Token 추가
            if (loginState) {
                if (authorizationToken.isNotEmpty()) {
                    addHeader(AUTHORIZATION, authorizationToken)
                    Log.d("로그 :: 토큰", authorizationToken)
                }
            }
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

class RetrofitProviderImpl(userPref: UserPref) : RetrofitProvider {
    private val DEFAULT_TIMEOUT: Long = 15
    private val _userPref = userPref

    override fun createClient() = OkHttpClient.Builder().apply {
//        // Debug용
//        val interceptor = HttpLoggingInterceptor()
//        interceptor.level = HttpLoggingInterceptor.Level.BODY
        retryOnConnectionFailure(true)
        writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
        readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
        connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
        callTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
        addInterceptor(ApplicationInterCeptor(userPref = _userPref))
        addNetworkInterceptor(NetWorkInterceptor())
    }.build()
}