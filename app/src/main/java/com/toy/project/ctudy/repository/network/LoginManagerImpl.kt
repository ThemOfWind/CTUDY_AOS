package com.toy.project.ctudy.repository.network

import com.toy.project.ctudy.model.LoginData
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
        return apiService.login(loginData).map {
            it.apply {
                if (result == true) {
                    userPref.setAccessToken(access_token)
                    userPref.setRefreshToken(refresh_token)
                    userPref.setTokenType(token_type)
                    userPref.setLoginId(loginData.username)

                    return@map it
                }else{
                    return@map Throwable("Login Error")
                }
            }
        }
    }

    override fun isLogin(): Boolean {
        if (userPref.getAccessToken()!!.isNotEmpty()) {
            return true
        }
        return false
    }
}