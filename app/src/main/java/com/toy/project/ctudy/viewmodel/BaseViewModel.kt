package com.toy.project.ctudy.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.toy.project.ctudy.common.SingleLiveEvent
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.internal.observers.ConsumerSingleObserver
import java.net.SocketTimeoutException

/**
 * Rxjava2 CompositeDisposeable class 사용
 * Observer를 android lifeCycle에 맞춰 해제할 수 있다
 *
 * 참고 : https://taehyungk.github.io/posts/android-RxJava2-Disposable//
 */
open class BaseViewModel : ViewModel() {
    private var compositeDisposable = CompositeDisposable()

    fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    fun clearDisposable() {
        compositeDisposable.clear()
    }

    // TODO 진행중
//    fun <T : Single<T>> T.startLoading(): Single<T> {  }

    override fun onCleared() {
        super.onCleared()
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.clear()
        }
    }
}