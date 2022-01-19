package com.toy.project.ctudy.repository.network

import android.util.Log
import com.toy.project.ctudy.model.LoginData
import com.toy.project.ctudy.model.response.BaseResponse
import com.toy.project.ctudy.model.response.LoginResponse
import com.toy.project.ctudy.repository.pref.UserPref
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Login Manager Impl
 */
class LoginManagerImpl(
    private val apiService: ApiService,
    private val userPref: UserPref,
) : LoginManager {
    override fun doLogin(loginData: LoginData): Single<Any> {
        return apiService.login(loginData)
            .map { result ->
                if (result.result == true) {
                    userPref.setAccessToken(result.response.access_token)
                    userPref.setRefreshToken(result.response.refresh_token)
                    userPref.setTokenType(result.response.token_type)
                    userPref.setLoginId(loginData.username)

                    return@map result
                } else {
                    return@map Throwable("Login Error")
                }
            }
            .flatMap { response ->
                when (response) {
                    is LoginResponse -> return@flatMap Single.create { it.onSuccess(response) }
                    else -> return@flatMap Single.create { it.onError(Throwable(response.toString())) }
                }
            }
    }

    override fun isLogin(): Boolean {
        if (userPref.getAccessToken()!!.isNotEmpty()) {
            return true
        }
        return false
    }

    override fun doLogout(): Single<Any> {
        return apiService.logout().map { response ->
            if (response.result == true) {
                userPref.setAccessToken("")
                userPref.setRefreshToken("")
                userPref.setTokenType("")

                return@map response
            } else {
                return@map Throwable("Logout Error")
            }
        }
            .flatMap { response ->
                when (response) {
                    is BaseResponse -> return@flatMap Single.create { it.onSuccess(response) }
                    else -> return@flatMap Single.create { it.onError(Throwable(response.toString())) }
                }
            }
    }
}