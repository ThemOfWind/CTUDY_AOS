package com.toy.project.ctudy.viewmodel

import androidx.lifecycle.MutableLiveData
import com.toy.project.ctudy.common.SignUpResponseType
import com.toy.project.ctudy.common.SingleLiveEvent
import com.toy.project.ctudy.extension.varifiacateLoginId
import com.toy.project.ctudy.model.SignUpData
import com.toy.project.ctudy.repository.network.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Sign ViewModel
 */
class SignViewModel(val apiService: ApiService) : BaseViewModel() {

    val _username = MutableLiveData<String>()
    val _password = MutableLiveData<String>()
    val _passwordConfirm = MutableLiveData<String>()
    val _name = MutableLiveData<String>()

    val dupleCheck = SingleLiveEvent<Boolean>()

    val completeSignOut = SingleLiveEvent<SignUpResponseType>()

    /**
     * 회원가입 API
     */
    fun doSignOut() {
        if (_username.value.isNullOrEmpty()) {
            completeSignOut.postValue(SignUpResponseType.EMPTY_ID)
        } else if (dupleCheck.value == false) {
            completeSignOut.postValue(SignUpResponseType.DUPLE_ID)
        } else if (_password.value.isNullOrEmpty()) {
            completeSignOut.postValue(SignUpResponseType.EMPTY_PASSWORD)
        } else if (_passwordConfirm.value.isNullOrEmpty() || !_passwordConfirm.value.equals(
                _password.value)
        ) {
            completeSignOut.postValue(SignUpResponseType.CONFIRM_PASSWORD)
        } else if (_name.value.isNullOrEmpty()) {
            completeSignOut.postValue(SignUpResponseType.EMPTY_NAME)
        } else {
            apiService.signUp(
                SignUpData(username = _username.toString(),
                    password = _password.toString(),
                    name = _name.toString()))
                .startLoading()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeDone({
                    completeSignOut.postValue(SignUpResponseType.DO_SIGNUP)
                }, {

                })
        }
    }

    /**
     * 아이디 중복검사 API
     */
    fun doCheckDupleId() {
        if (!_username.value.isNullOrEmpty()) {
            if (varifiacateLoginId(_username.value.toString())) {
                apiService.dupleIdConfirm(username = _username.value.toString())
                    .startLoading()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeDone({
                        dupleCheck.postValue(true)
                    }, {
                        dupleCheck.postValue(false)
                    })
            } else {
                completeSignOut.postValue(SignUpResponseType.CONFIRM_ID)
            }
        } else {
            completeSignOut.postValue(SignUpResponseType.EMPTY_ID)
        }
    }
}