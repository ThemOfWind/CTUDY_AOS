package com.toy.project.ctudy

import android.app.Application
import com.toy.project.ctudy.di.ViewModelModule
import com.toy.project.ctudy.di.apiModule
import com.toy.project.ctudy.di.dialogModule
import com.toy.project.ctudy.di.prefModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * Base Application 생성
 *
 * Rxjava 참고 : http://blog.yena.io/studynote/2020/10/11/Android-RxJava(1).html
 */
class CtudyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        /**
         * Koin을 통한 의존성 주입
         *
         * 각 Activity의 context의 영향받지 않고 공통으로 재사용할 수 있도록 한다.
         * 참고 : https://spoqa.github.io/2020/11/02/android-dependency-injection-with-koin.html
         */
        startKoin {
            // 해당 안드로이드 context를 사용
            androidContext(this@CtudyApplication)
            // 사용할 모듈 등록
            modules(
                listOf(
                    apiModule,
                    prefModule,
                    ViewModelModule,
                    dialogModule
                ))
        }
    }
}