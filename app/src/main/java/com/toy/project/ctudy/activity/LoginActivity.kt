package com.toy.project.ctudy.activity

import android.os.Bundle
import com.toy.project.ctudy.BR
import com.toy.project.ctudy.R
import com.toy.project.ctudy.databinding.ActivityLoginBindingImpl
import com.toy.project.ctudy.repository.network.LoginManager
import com.toy.project.ctudy.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Login Page
 */
class LoginActivity : BaseActivity<ActivityLoginBindingImpl, LoginViewModel>() {
    override val layoutResID = R.layout.activity_login
    override val viewModel: LoginViewModel by viewModel()
    override val viewModelVariable = BR.viewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(viewModel){

        }
    }
}