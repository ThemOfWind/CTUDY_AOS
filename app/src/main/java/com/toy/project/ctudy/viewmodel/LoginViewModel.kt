package com.toy.project.ctudy.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.toy.project.ctudy.common.LoginEditErrorType
import com.toy.project.ctudy.common.SingleLiveEvent
import com.toy.project.ctudy.extension.varifiacateLoginId
import com.toy.project.ctudy.model.LoginData
import com.toy.project.ctudy.model.response.LoginResponse
import com.toy.project.ctudy.repository.network.LoginManager
import com.toy.project.ctudy.repository.pref.UserPref
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.regex.Pattern

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
    val loginEditType = SingleLiveEvent<LoginEditErrorType>()

    init {
        if (!userPref.getLoginId().isNullOrEmpty()) {
            userId.value = userPref.getLoginId()
        }
    }

    /**
     * 로그인
     */
    fun requestLogin() {
        // Id, Password DataBinding 안됨...
        // 일단 임의로 test 데이터로.......
        if (!userId.value.isNullOrBlank() && !password.value.isNullOrBlank()) {
            if (varifiacateLoginId(userId.value.toString().trim())) {
                // 아이디 이메일 형식 검증 성공
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
                                    Log.d("유저 AccessToken : ", userPref.getAccessToken().toString())
                                    loginState.value = true
                                }
                                else -> {
                                    loginState.value = false
                                }
                            }
                        }, {
                            loginState.value = false
                        })
                )
            } else {
                loginEditType.postValue(LoginEditErrorType.VARIFICATION_ID)
            }
        } else {
            if (userId.value.isNullOrBlank()) {
                loginEditType.postValue(LoginEditErrorType.EMPTY_ID)
            } else if (password.value.isNullOrBlank()) {
                loginEditType.postValue(LoginEditErrorType.EMPTY_PASSWORD)
            }
        }
    }

    /**
     * 자동 로그인 체크 시 세팅될 정보
     */
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