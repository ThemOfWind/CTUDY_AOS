package com.toy.project.ctudy.di

import android.content.Context
import android.content.SharedPreferences
import com.toy.project.ctudy.repository.pref.UserPref
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * Shared Pref 관리 공통 모듈
 */
val prefModule = module {
    // single : 앱이 실행되는 동안 유지되는 싱글톤 객체를 생성한다
    // factory : 요청할 때마다 매번 새로운 객체를 생성한다
    // get() : 컴포넌트 내에서 알맞은 의존성을 주입받는다

    val PREF_NAME = "pref_module"

    factory<SharedPreferences> {
        androidContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    single {
        UserPref(get())
        // SharedPreference 타입으로 선언되어 있어 get() 으로 해당 객체를 주입 받는다
    }
}