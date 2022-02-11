package com.toy.project.ctudy.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.toy.project.ctudy.activity.MainActivity
import com.toy.project.ctudy.common.SingleLiveEvent
import com.toy.project.ctudy.extension.singleStartActivity
import com.toy.project.ctudy.model.response.BaseResponse
import com.toy.project.ctudy.model.response.RoomAllResponseList
import com.toy.project.ctudy.repository.network.ApiService
import com.toy.project.ctudy.repository.network.LoginManager
import com.toy.project.ctudy.repository.pref.UserPref
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Main ViewModel
 */
class MainViewModel(
    val apiService: ApiService,
    val loginManager: LoginManager,
) : BaseViewModel() {

    val loginState = SingleLiveEvent<Boolean>()
    val menuFab = SingleLiveEvent<Boolean>()
    val addRoomFab = SingleLiveEvent<Unit>()

    val mainRoomList = MutableLiveData<ArrayList<RoomAllResponseList>>()

    init {
        getStudyRoomList()
    }

    /**
     * 메인 룸 스터디 리스트 API 조회
     */
    fun getStudyRoomList() {
        addDisposable(
            apiService.studyAllRoomInquiry()
                .startLoading()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeDone({
                    if (it.result) {
                        mainRoomList.postValue(it.responseList)
                        Log.d("로그 :: 메인 리스트 성공", it.responseList.size.toString())
                    }
                }, {
                    loginState.postValue(true)
                })
        )
    }

    /**
     * 로그아웃 API
     */
    fun logOut() {
        addDisposable(
            loginManager.doLogout()
                .startLoading()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeDone({
                    when (it) {
                        is BaseResponse -> {
                            loginState.postValue(true)
                        }
                        else -> {
                            loginState.postValue(false)
                        }
                    }
                }, {
                    loginState.postValue(false)
                })
        )
    }


    /**
     * 메뉴 Floating Button 클릭
     */
    fun clickMenuFab() {
        if (menuFab.value == false) {
            menuFab.postValue(true)
        } else {
            menuFab.postValue(false)
        }
    }

    /**
     * 스터디 룸 등록 페이지 이동
     */
    fun moveEnrollRoom() {
        addRoomFab.call()
    }
}