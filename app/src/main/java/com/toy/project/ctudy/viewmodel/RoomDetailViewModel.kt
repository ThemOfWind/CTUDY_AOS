package com.toy.project.ctudy.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.toy.project.ctudy.common.SingleLiveEvent
import com.toy.project.ctudy.repository.network.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RoomDetailViewModel(
    val apiService: ApiService,
) : BaseViewModel() {

    var id: String = ""


    val resultName = MutableLiveData<String>()

    val doModify = SingleLiveEvent<Unit>()
    val doDelete = SingleLiveEvent<Unit>()

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
                        doDelete.call()
                    }
                }, {

                }))

    }


    fun viewModelFactory(): ViewModelProvider.Factory {
        return RoomDetailViewFactory(apiService)
    }

    class RoomDetailViewFactory(private val apiService: ApiService) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return RoomDetailViewModel(apiService) as T
        }
    }
}