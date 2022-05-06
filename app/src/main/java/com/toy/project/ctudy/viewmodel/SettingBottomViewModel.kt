package com.toy.project.ctudy.viewmodel

import com.toy.project.ctudy.common.ResponseType
import com.toy.project.ctudy.common.SingleLiveEvent
import com.toy.project.ctudy.repository.network.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SettingBottomViewModel(val apiService: ApiService) :
    BaseViewModel() {

    var id: String = ""

    val doDelete = SingleLiveEvent<ResponseType>()
    val doModify = SingleLiveEvent<ResponseType>()

    /**
     * 스터디 룸 수정 팝업 노출
     */
    fun showModifyDialog() {
        doModify.call()
    }

    /**
     * 스터디 룸 삭제
     */
    fun roomDelete() {
        addDisposable(
            apiService.studyRoomDelete(id = id)
                .startLoading()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeDone({
                    if (it.result) {
                        doDelete.postValue(ResponseType.SUCCESS)
                    }
                }, {
                    doDelete.postValue(ResponseType.FAIL)
                }))

    }
}