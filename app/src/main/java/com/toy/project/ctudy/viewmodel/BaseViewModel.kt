package com.toy.project.ctudy.viewmodel

import androidx.lifecycle.ViewModel
import com.toy.project.ctudy.common.LoadingDialogType
import com.toy.project.ctudy.common.NetWorkDialogType
import com.toy.project.ctudy.common.SingleLiveEvent
import com.toy.project.ctudy.model.response.LoginResponse
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.internal.observers.ConsumerSingleObserver

/**
 * Rxjava2 CompositeDisposeable class 사용
 * Observer를 android lifeCycle에 맞춰 해제할 수 있다
 *
 * 참고 : https://taehyungk.github.io/posts/android-RxJava2-Disposable//
 */
open class BaseViewModel : ViewModel() {
    val networkAlertDialogState = SingleLiveEvent<Int>()
    val startLoadingDialogState = SingleLiveEvent<LoadingDialogType>()

    private var compositeDisposable = CompositeDisposable()

    fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    fun clearDisposable() {
        compositeDisposable.clear()
    }

    // API호출 시 노출될 공통 Loading Dialog
    // 확장함수 (Extention) 사용
    // Rxjava의 생명주기 따라 구독 시작하였을 시점에 (doOnSubscribe) Dialog 노출하도록 한다.
    /**
     * Rxjava 생명주기 참고 : https://brunch.co.kr/@lonnie/17
     *
     * doOnSubscribe :  Observer가 구독될 때 호출되는 콜백함수 등록 가능하다.
     * doOnUnSubscribe : Observer가 구독 해제될 때 호출
     * doOnNext : Observer가 아이템을 발행할 때 호출
     * doOnCompleted : Observer가 완료를 발행할 때 호출
     * doOnError : Observer가 에러를 발행할 때 호출
     * doOnEach : Observer가 아이템, 완료, 에러 발행할 때 호출 -> 파라미터로 Notification 객체 전달
     * ---->  Notification에 따라 아이템이나 이벤트 타입 알 수 있다.
     * doOnTerminate : Observer가 완료, 에러 발행할 때 호출
     */
    protected fun <A : Any, S : Single<A>> S.startLoading(): Single<A> =
        doOnSubscribe { showDialog() }

    protected fun <A : Any, S : Single<A>> S.subscribeDone(
        success: (A) -> Unit, fail: () -> Unit,
    ): Disposable {
        val observer: ConsumerSingleObserver<A> = ConsumerSingleObserver({ response ->
            when (response) {
                is LoginResponse -> {
                    if (!response.result) {
                        // response Fail
                        networkAlertDialogState.postValue(NetWorkDialogType.ETC_ERROR.msg)
                    }
                }
            }
            dissmissDialog()
            success.invoke(response)
        }, {
            networkAlertDialogState.postValue(NetWorkDialogType.ETC_ERROR.msg)
            dissmissDialog()
            fail.invoke()
        })
        dissmissDialog()
        subscribe(observer)
        return observer
    }

    fun showDialog() {
        startLoadingDialogState.postValue(LoadingDialogType.SHOW)
    }

    fun dissmissDialog() {
        startLoadingDialogState.postValue(LoadingDialogType.DISMISS)
    }

    override fun onCleared() {
        super.onCleared()
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.clear()
        }
    }
}