package com.toy.project.ctudy.viewmodel

import androidx.lifecycle.MutableLiveData
import com.toy.project.ctudy.model.LoginData
import com.toy.project.ctudy.repository.network.LoginManager
import com.toy.project.ctudy.repository.pref.UserPref
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

    fun doLogin() {
        if (userId.value.isNullOrBlank() && password.value.isNullOrBlank()) {
            addDisposable(
                loginManager.doLogin(
                    LoginData(username = userId.value.toString(),
                        password = password.value.toString()))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({

                    }, {

                    }))
        }
    }
}