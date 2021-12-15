package com.toy.project.ctudy.viewmodel

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

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

    fun clearDisposable(){
        compositeDisposable.clear()
    }

    override fun onCleared() {
        super.onCleared()
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.clear()
        }
    }
}