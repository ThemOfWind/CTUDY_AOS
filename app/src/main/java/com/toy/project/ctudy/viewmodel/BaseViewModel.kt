package com.toy.project.ctudy.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.toy.project.ctudy.common.AlertDialogBtnType
import com.toy.project.ctudy.common.LoadingDialogType
import com.toy.project.ctudy.common.NetWorkDialogType
import com.toy.project.ctudy.common.SingleLiveEvent
import com.toy.project.ctudy.model.response.BaseResponse
import com.toy.project.ctudy.repository.etc.CommonDialogListener
import com.toy.project.ctudy.view.CommonDialog
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.internal.observers.ConsumerSingleObserver
import org.json.JSONObject
import retrofit2.HttpException


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
        success: (A) -> Unit, fail: (String) -> Unit,
    ): Disposable {
        val observer: ConsumerSingleObserver<A> = ConsumerSingleObserver({ response ->
            when (response) {
                is BaseResponse -> {
                    if (!response.result) {
                        // response Fail
                        networkAlertDialogState.postValue(NetWorkDialogType.ETC_ERROR.msg)
                    }
                }
            }
            dissmissDialog()
            success.invoke(response)
        }, {
            // 현재 response success가 아닐 경우
            // 200 이외의 코드가 떨어져 이 경우 json body 생성 및 가공한다
            val error = it as HttpException
            val errorBody = error.response()?.errorBody()?.string()
            val message = responseErrorBody(errorBody.toString())
            dissmissDialog()
            // fail일 경우 콜백메소드에 에러메세지 넘김
            fail.invoke(message)
        })
        dissmissDialog()
        subscribe(observer)
        return observer
    }

    fun responseErrorBody(error: String): String {
        val jsonObject = JSONObject(error)
        val errorBody = jsonObject.getJSONObject("error")
        return errorBody.getString("message")
    }

    fun showDialog() {
        startLoadingDialogState.postValue(LoadingDialogType.SHOW)
    }

    fun dissmissDialog() {
        startLoadingDialogState.postValue(LoadingDialogType.DISMISS)
    }

    /**
     * One Or Two 버튼 공통 팝업 정의
     * 버튼별 리스너는 리턴되는 CommonDialog 내에서 각각 정의되도록 한다.
     */
    protected fun showCommonDialog(
        context: Context,
        type: AlertDialogBtnType,
        msg: String,
    ): CommonDialog {
        return CommonDialog(
            context = context
        ).apply {
            if (type == AlertDialogBtnType.ONE) {
                setOneButtonType()

                dialogClick(object : CommonDialogListener {
                    override fun onConfirm() {
                        dismiss()
                    }

                    override fun onCancle() {
                        dismiss()
                    }
                })
            }
            setContentMsg(msg)
            show()
        }
    }

    override fun onCleared() {
        super.onCleared()
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.clear()
        }
    }
}