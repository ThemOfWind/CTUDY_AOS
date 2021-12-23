package com.toy.project.ctudy.activity

import android.os.Bundle
import android.view.View
import com.toy.project.ctudy.R
import com.toy.project.ctudy.databinding.ActivityLoginBindingImpl
import com.toy.project.ctudy.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Login Page
 */
class LoginActivity : BaseActivity<ActivityLoginBindingImpl, LoginViewModel>(),
    View.OnClickListener {
    override val layoutResID: Int = R.layout.activity_login
    override val viewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            viewBinding.loginBtn -> viewModel.doLogin()
        }
    }
}