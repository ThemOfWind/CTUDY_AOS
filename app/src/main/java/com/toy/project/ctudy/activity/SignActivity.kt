package com.toy.project.ctudy.activity

import android.os.Bundle
import com.toy.project.ctudy.BR
import com.toy.project.ctudy.R
import com.toy.project.ctudy.databinding.ActivitySignBinding
import com.toy.project.ctudy.viewmodel.SignViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * 회원가입 페이지
 */
class SignActivity : BaseActivity<ActivitySignBinding, SignViewModel>() {
    override val layoutResID: Int = R.layout.activity_sign
    override val viewModel: SignViewModel by viewModel()
    override val viewModelVariable: Int = BR.viewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}