package com.toy.project.ctudy.viewmodel

import androidx.lifecycle.MutableLiveData
import com.toy.project.ctudy.common.LoginEditErrorType
import com.toy.project.ctudy.common.SingleLiveEvent
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
        // test
        userId.value = "yh@naver.com"
    }

    /**
     * 로그인
     */
    fun requestLogin() {
        // Id, Password DataBinding 안됨...
        // 일단 임의로 test 데이터로.......
        if (!userId.value.isNullOrBlank() && !password.value.isNullOrBlank()) {
            if (varifiacateLoginId(userId.value.toString())) {
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
     * 이메일 형식 검증
     */
    fun varifiacateLoginId(id: String): Boolean {
        val emailReg = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";
        val pattern = Pattern.compile(emailReg)
        val matcher = pattern.matcher(id)
        if (matcher.matches())
            return true
        return false
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