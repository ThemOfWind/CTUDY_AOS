package com.toy.project.ctudy.viewmodel

import androidx.lifecycle.MutableLiveData
import com.toy.project.ctudy.common.ResponseType
import com.toy.project.ctudy.common.SingleLiveEvent
import com.toy.project.ctudy.model.RoomModifyData
import com.toy.project.ctudy.repository.etc.CommonDialogManager
import com.toy.project.ctudy.repository.network.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RoomModifyViewModel(
    val apiService: ApiService,
    commonDialogManager: CommonDialogManager,
) : BaseViewModel() {

    val commonDialog = commonDialogManager

    val name = MutableLiveData<String>()
    val master = MutableLiveData<String>()
    val id = MutableLiveData<String>()

    val successModify = SingleLiveEvent<ResponseType>()
    val isNameEmpty = SingleLiveEvent<Boolean>()

    /**
     * 스터디 룸 수정
     */
    fun roomModify() {
        if (!isNameEmpty()) {
            addDisposable(
                apiService.studyRoomModify(modifyData = RoomModifyData(
                    name.value.toString(),
                ),
                    id = id.value.toString())
                    .startLoading()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeDone({
                        if (it.result) {
                            successModify.postValue(ResponseType.SUCCESS)
                        }
                    }, {
                        successModify.postValue(ResponseType.FAIL)
                    }))
        } else {
            isNameEmpty.postValue(true)
        }
    }

    fun isNameEmpty(): Boolean {
        if (name.value.isNullOrEmpty()) {
            return true
        }
        return false
    }
}