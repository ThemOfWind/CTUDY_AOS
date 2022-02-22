package com.toy.project.ctudy.viewmodel

import androidx.lifecycle.MutableLiveData
import com.toy.project.ctudy.repository.network.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RoomDetailViewModel(
    val apiService: ApiService,
) : BaseViewModel() {

    var name = MutableLiveData<String>()

    fun getRoomDetail(id: String) {
        addDisposable(
            apiService.studyRoomDetail(id)
                .startLoading()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeDone({
                    if (it.result) {
                        val _name = it.response.name
                        name.value = _name
                    }
                }, {

                }))
    }
}