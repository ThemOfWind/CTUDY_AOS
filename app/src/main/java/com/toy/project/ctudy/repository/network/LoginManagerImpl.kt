package com.toy.project.ctudy.repository.network

import com.toy.project.ctudy.model.LoginData
import com.toy.project.ctudy.repository.pref.UserPref
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 *
 */
class LoginManagerImpl(
    private val apiService: ApiService,
    private val userPref: UserPref,
) : LoginManager {
    override fun doLogin(loginData: LoginData): Disposable {
        return apiService.login(loginData)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { response, throwable ->
                response.apply {
                    if (result == true) {
                        userPref.setAccessToken(access_token)
                        userPref.setRefreshToken(refresh_token)
                        userPref.setTokenType(token_type)
                        userPref.setLoginId(loginData.username)
                    } else {

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