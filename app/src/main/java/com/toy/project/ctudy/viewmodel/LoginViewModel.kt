package com.toy.project.ctudy.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.toy.project.ctudy.common.SingleLiveEvent
import com.toy.project.ctudy.model.LoginData
import com.toy.project.ctudy.model.response.LoginResponse
import com.toy.project.ctudy.repository.network.LoginManager
import com.toy.project.ctudy.repository.pref.UserPref
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlin.math.log

/**
 * Login ViewModel
 */
class LoginViewModel(
    val loginManager: LoginManager,
    private val userPref: UserPref,
) : BaseViewModel() {

    val userId = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val checkAutoLogin = MutableLiveData(false)

    val loginState = SingleLiveEvent<Boolean>()

    fun requestLogin() {
        // Id, Password DataBinding 안됨...
        // 일단 임의로 test 데이터로.......
        userId.value = "test1"
        password.value = "1234"
        if (!userId.value.isNullOrBlank() && !password.value.isNullOrBlank()) {
            addDisposable(
                loginManager.doLogin(
                    LoginData(username = userId.value!!.trim(),
                        password = password.value!!.trim()))
                    .startLoading()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeDone({
                        when (it) {
                            is LoginResponse -> {
                                loginState.value = true
                            }
                            else -> {
                                loginState.value = false
                            }
                        }
                    },{
                        loginState.value = false
                    })
            )
        }
    }


    fun setAutoLoginFlag() {
        if (checkAutoLogin.value == true) {
            checkAutoLogin.value = false
            userPref.setAutoLogin(false)
        } else {
            checkAutoLogin.value = true
            userPref.setAutoLogin(true)
        }
    }
}