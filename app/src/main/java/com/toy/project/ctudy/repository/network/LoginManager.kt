package com.toy.project.ctudy.repository.network

import com.toy.project.ctudy.model.LoginData
import io.reactivex.Single
import io.reactivex.disposables.Disposable

/**
 * Login 관련 수행 Function 정의
 */
interface LoginManager {
    fun doLogin(loginData: LoginData): Disposable
    fun isLogin(): Boolean
}