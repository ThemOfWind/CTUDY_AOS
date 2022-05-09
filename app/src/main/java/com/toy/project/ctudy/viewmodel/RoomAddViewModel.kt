package com.toy.project.ctudy.viewmodel

import androidx.lifecycle.MutableLiveData
import com.toy.project.ctudy.common.SingleLiveEvent
import com.toy.project.ctudy.repository.network.ApiService

class RoomAddViewModel(
    val apiService: ApiService,
) : BaseViewModel() {

    val roomName = MutableLiveData<String>()
    val addNext = SingleLiveEvent<Unit>()

    /**
     * 스터디 룸 등록
     */
    fun nextEnrollRoom() {
        addNext.call()
    }
}