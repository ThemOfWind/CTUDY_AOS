package com.toy.project.ctudy.viewmodel

import androidx.lifecycle.MutableLiveData
import com.toy.project.ctudy.common.SingleLiveEvent
import com.toy.project.ctudy.model.response.BaseResponse
import com.toy.project.ctudy.repository.network.LoginManager
import com.toy.project.ctudy.repository.pref.UserPref
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Main ViewModel
 */
class MainViewModel(
    val loginManager: LoginManager,
    private val userPref: UserPref,
) : BaseViewModel() {

    val userData = MutableLiveData<UserPref>()
    val loginState = SingleLiveEvent<Boolean>()

    fun logOut() {
        addDisposable(
            loginManager.doLogout()
                .startLoading()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeDone({
                    when (it) {
                        is BaseResponse -> {
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
    }
}