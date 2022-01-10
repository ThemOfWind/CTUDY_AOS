package com.toy.project.ctudy.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleObserver
import com.toy.project.ctudy.common.AlertDialogBtnType
import com.toy.project.ctudy.common.LoadingDialogType
import com.toy.project.ctudy.common.LoginEditErrorType
import com.toy.project.ctudy.repository.etc.CommonDialogListener
import com.toy.project.ctudy.repository.etc.CommonDialogManager
import com.toy.project.ctudy.view.CommonDialog
import com.toy.project.ctudy.view.LoadingDialog
import com.toy.project.ctudy.viewmodel.BaseViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

/**
 * Base Activity 정의
 *
 * DataBinding, ViewModel, LifecycleObsever ( LiveData )
 * 참고 : https://jinee0717.tistory.com/33
 */
abstract class BaseActivity<DataBinding : ViewDataBinding, R : BaseViewModel> : AppCompatActivity(),
    LifecycleObserver, KoinComponent {

    lateinit var viewBinding: DataBinding

    abstract val layoutResID: Int
    abstract val viewModel: R
    abstract val viewModelVariable: Int
    private var loadingDialog: LoadingDialog? = null
    private val commonDialogManager: CommonDialogManager by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<DataBinding>(this, layoutResID).apply {
            lifecycleOwner = this@BaseActivity
            setVariable(viewModelVariable, viewModel)
            viewBinding = this
        }

        viewModel.startLoadingDialogState.observe(this@BaseActivity, {
            if (it == LoadingDialogType.SHOW) {
                showLoadingDialog()
            } else {
                dismissLoadingDialog()
            }
        })

        viewModel.networkAlertDialogState.observe(this@BaseActivity, {
            networkAlertDialog(this@BaseActivity.resources.getString(it))
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        // Activity 리소스 해제
        viewModel.run {
            clearDisposable()
        }
    }

    /**
     * 네트워크 오류 시 노출될 팝업 정의
     */
    protected fun networkAlertDialog(msg: String) {
        showCommonDialog(
            AlertDialogBtnType.ONE,
            msg).run {
            dialogClick(object : CommonDialogListener {
                override fun onConfirm() {
                    dismiss()
                }

                override fun onCancle() {
                    dismiss()
                }
            })
        }
    }

    /**
     * One Or Two 버튼 공통 팝업 정의
     * 버튼별 리스너는 리턴되는 CommonDialog 내에서 각각 정의되도록 한다.
     */
    protected fun showCommonDialog(
        type: AlertDialogBtnType,
        msg: String,
    ): CommonDialog {
        return CommonDialog(
            context = this
        ).apply {
            if (type == AlertDialogBtnType.ONE) {
                setOneButtonType()
            }
            setContentMsg(msg)
            show()
        }
    }

    /**
     * Loading Dialog Show
     */
    protected fun showLoadingDialog() {
        if (isFinishing) {
            return
        }

        loadingDialog?.let {
            if (!it.isShowing) {
                it.show()
            }
        } ?: run {
            // apply 와 유사하다
            // apply : 새로운 객체를 생성함과 동시에 연속된 작업이 필요할 때 사용
            // run : 이미 생성된 객체에 연속된 작업이 필요할 때 사용한다.
            loadingDialog = LoadingDialog(this).run {
                show()
                this
            }
        }
    }

    /**
     * Loading Dialog Dismiss
     */
    protected fun dismissLoadingDialog() {
        loadingDialog?.let {
            if (it.isShowing) {
                it.dismiss()
            }
        }
        loadingDialog = null
    }
}