package com.toy.project.ctudy.viewmodel

import com.toy.project.ctudy.common.SingleLiveEvent
import com.toy.project.ctudy.model.RoomModifyData
import com.toy.project.ctudy.repository.network.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RoomDetailViewModel(
    val apiService: ApiService,
) : BaseViewModel() {

    var id: String = ""

    var resultName = SingleLiveEvent<String>()
    val doModify = SingleLiveEvent<Unit>()

    /**
     * 스터디 룸 상세 api
     */
    fun getRoomDetail() {
        addDisposable(
            apiService.studyRoomDetail(id)
                .startLoading()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeDone({
                    if (it.result) {
                        resultName.postValue(it.response.name)
                    }
                }, {

                }))
    }

    /**
     * 스터디 룸 수정 팝업 노출
     */
    fun showModifyDialog() {
        doModify.call()
    }

    /**
     * 스터디 룸 수정
     */
    fun roomModify(name: String, master: String) {
        addDisposable(
            apiService.studyRoomModify(modifyData = RoomModifyData(name, master), id = id)
                .startLoading()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeDone({
                    if (it.result) {

                    }
                }, {

                }))

    }
}