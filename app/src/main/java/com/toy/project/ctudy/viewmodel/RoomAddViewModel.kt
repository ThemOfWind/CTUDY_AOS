package com.toy.project.ctudy.viewmodel

import androidx.lifecycle.MutableLiveData
import com.toy.project.ctudy.common.SingleLiveEvent
import com.toy.project.ctudy.model.RoomEnrollData
import com.toy.project.ctudy.repository.network.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RoomAddViewModel(
    val apiService: ApiService,
) : BaseViewModel() {

    val roomName = MutableLiveData<String>()
    val enrollRoomState = SingleLiveEvent<Boolean>()

    /**
     * 스터디 룸 등록
     */
    fun enrollRoom() {
        addDisposable(
            apiService.studyRoomEnroll(RoomEnrollData(roomName.value.toString()))
                .startLoading()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeDone({
                    if (it.result)
                        enrollRoomState.postValue(true)
                    else
                        enrollRoomState.postValue(false)
                }, {
                    enrollRoomState.postValue(false)
                })
        )
    }
}