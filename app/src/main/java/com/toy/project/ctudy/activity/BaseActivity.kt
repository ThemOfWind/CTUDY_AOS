package com.toy.project.ctudy.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleObserver
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<DataBinding>(this, layoutResID).apply {
            lifecycleOwner = this@BaseActivity
            setVariable(viewModelVariable, viewModel)
            viewBinding = this
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // Activity 리소스 해제
        viewModel.run {
            clearDisposable()
        }
    }

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

    protected fun dismissLoadingDialog() {
        loadingDialog?.let {
            if (it.isShowing) {
                it.dismiss()
            }
        }
        loadingDialog = null
    }
}