package com.toy.project.ctudy.viewmodel

import androidx.lifecycle.MutableLiveData
import com.toy.project.ctudy.model.LoginData
import com.toy.project.ctudy.repository.network.LoginManager
import com.toy.project.ctudy.repository.pref.UserPref

/**
 * Login ViewModel
 */
class LoginViewModel(
    private val loginManager: LoginManager,
    private val userPref: UserPref,
) : BaseViewModel() {

    val userId = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    init {

    }

    fun doLogin() {
        addDisposable(
            loginManager.doLogin(
                LoginData(username = userId.value.toString(), password = password.value.toString())))
    }
}