package com.toy.project.ctudy.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.toy.project.ctudy.model.LoginData
import com.toy.project.ctudy.model.response.LoginResponse
import com.toy.project.ctudy.repository.network.LoginManager
import com.toy.project.ctudy.repository.pref.UserPref
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Login ViewModel
 */
class LoginViewModel(
    private val loginManager: LoginManager,
    private val userPref: UserPref,
) : BaseViewModel() {

    val userId = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    fun requestLogin() {
        // Id, Password DataBinding 안됨...
        // 일단 임의로 test 데이터로.......
        userId.value = "test1"
        password.value = "12345"
        if (!userId.value.isNullOrBlank() && !password.value.isNullOrBlank()) {
            addDisposable(
                loginManager.doLogin(
                    LoginData(username = userId.value!!.trim(),
                        password = password.value!!.trim()))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        when (it) {
                            is LoginResponse -> Log.d("성공", "Success")
                            else -> Log.d("실패", "Fail")
                        }
                    }, {
                        Log.d("실패", "Throwable")
                    }))
        }
    }
}