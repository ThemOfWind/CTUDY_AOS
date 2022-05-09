package com.toy.project.ctudy.viewmodel

import com.toy.project.ctudy.common.SingleLiveEvent
import com.toy.project.ctudy.model.RoomEnrollData
import com.toy.project.ctudy.model.response.MemberList
import com.toy.project.ctudy.repository.network.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MemberEnrollViewModel(val apiService: ApiService) :
    BaseViewModel() {

    // 기본 멤버 리스트 갯수
    var DEFAULT_MEMBER_COUNT = 10

    var roomName = ""
    var memberList = SingleLiveEvent<ArrayList<MemberList>>()

    val enrollRoomState = SingleLiveEvent<Boolean>()

    init {
        getMemberList()
    }

    /**
     * 멤버 조회
     *
     */

    fun getMemberList() {
        addDisposable(
            apiService.studyMemberList(1, DEFAULT_MEMBER_COUNT)
                .startLoading()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeDone({
                    if (it.result)
                        if (it.response.memberList.size > 0) {
                            memberList.postValue(it.response.memberList)
                        }
                }, {

                })
        )
    }

    /**
     * 스터디 룸 등록
     */
    fun enrollRoomInfo() {
        addDisposable(
            apiService.studyRoomEnroll(RoomEnrollData(roomName))
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