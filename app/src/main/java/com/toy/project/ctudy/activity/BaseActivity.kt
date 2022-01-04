package com.toy.project.ctudy.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleObserver
import com.toy.project.ctudy.common.AlertDialogType
import com.toy.project.ctudy.common.LoadingDialogType
import com.toy.project.ctudy.view.CommonDialog
import com.toy.project.ctudy.view.LoadingDialog
import com.toy.project.ctudy.viewmodel.BaseViewModel
import org.koin.core.component.KoinComponent

/**
 * BaseActitivity 정의
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
    private var alertDialog: CommonDialog? = null

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

        viewModel.commonAlertDialogState.observe(this@BaseActivity, {
            alertDialog(it.name, it.msg)
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        // Activity 리소스 해제
        viewModel.run {
            clearDisposable()
        }
    }

    protected fun alertDialog(type: String, msg: String) {
        // TODO 타입에 따라 one,two버튼 지정?
        alertDialog = CommonDialog(this).run {
            if (type.equals(AlertDialogType.ETC_ERROR)
                || type.equals(AlertDialogType.NETWORK_ERROR)
            ) {

            }
            setContentMsg(msg)
            this
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