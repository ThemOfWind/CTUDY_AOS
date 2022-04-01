package com.toy.project.ctudy.activity

import android.os.Bundle
import android.view.View
import com.toy.project.ctudy.BR
import com.toy.project.ctudy.R
import com.toy.project.ctudy.common.AlertDialogBtnType
import com.toy.project.ctudy.databinding.ActivityLoginBinding
import com.toy.project.ctudy.extension.singleStartActivity
import com.toy.project.ctudy.extension.startMoveActivity
import com.toy.project.ctudy.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * 로그인 페이지
 */
class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>(),
    View.OnClickListener {
    override val layoutResID = R.layout.activity_login
    override val viewModel: LoginViewModel by viewModel()
    override val viewModelVariable = BR.viewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(viewModel) {
            loginState.observe(this@LoginActivity, {
                if (it)
                    singleStartActivity<MainActivity>()
                else
                    showCommonDialog(AlertDialogBtnType.ONE,
                        this@LoginActivity.resources.getString(R.string.login_error_text))
            })

            loginEditType.observe(this@LoginActivity, {
                showCommonDialog(AlertDialogBtnType.ONE,
                    this@LoginActivity.resources.getString(it.msg))
            })
        }
        viewBinding.loginSignIn.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view) {
            viewBinding.loginSignIn -> startMoveActivity<SignActivity>()
        }
    }
}