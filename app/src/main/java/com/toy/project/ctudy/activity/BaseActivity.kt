package com.toy.project.ctudy.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleObserver
import com.toy.project.ctudy.viewmodel.BaseViewModel
import org.koin.core.component.KoinComponent

/**
 * BaseActitivity 정의
 * DataBinding, ViewModel, LifecycleObsever ( LiveData )
 * ******** DI도 추가 필요 ( Koin 아니면 Hilt...? )
 * 참고 : https://jinee0717.tistory.com/33
 */
abstract class BaseActivity<DataBinding : ViewDataBinding, R : BaseViewModel> : AppCompatActivity(),
    LifecycleObserver, KoinComponent {

    lateinit var viewBinding: DataBinding

    abstract val layoutResID: Int
    abstract val viewModel: R

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<DataBinding>(this, layoutResID).apply {
            lifecycleOwner = this@BaseActivity
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
}