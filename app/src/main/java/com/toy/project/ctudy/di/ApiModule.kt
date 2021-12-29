package com.toy.project.ctudy.di

import com.google.gson.GsonBuilder
import com.toy.project.ctudy.common.HttpDefine
import com.toy.project.ctudy.repository.network.*
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Retrofit 공통 모듈
 * 참고 : https://zzandoli.tistory.com/3
 */
val apiModule = module {

    single<RetrofitProvider> {
        RetrofitProviderImpl()
    }

    single<ApiService> {
        var gson = GsonBuilder().setLenient().create()
        val provider: RetrofitProvider = get()
        Retrofit.Builder().apply {
            baseUrl(HttpDefine.CTUDY_DOMAIN)
            client(provider.createClient())
            addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            addConverterFactory(GsonConverterFactory.create(gson))
        }.build().create(ApiService::class.java)
    }

    single<LoginManager> {
        LoginManagerImpl(get(), get())
    }
}