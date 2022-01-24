package com.toy.project.ctudy.viewmodel

import androidx.lifecycle.MutableLiveData
import com.toy.project.ctudy.common.SingleLiveEvent
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
        // 메인 룸 스터디 리스트 API 조회
        addDisposable(
            apiService.studyAllRoomInquiry()
                .startLoading()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeDone({
                    if (it.result) {
                        mainRoomList.postValue(it.responseList)
                    }
                }, {

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