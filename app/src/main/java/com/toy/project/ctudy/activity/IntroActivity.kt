package com.toy.project.ctudy.activity

import android.os.Bundle
import com.toy.project.ctudy.R
import com.toy.project.ctudy.databinding.ActivityIntroBinding
import com.toy.project.ctudy.viewmodel.BaseViewModel
import com.toy.project.ctudy.viewmodel.IntroViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Intro Page
 */
class IntroActivity : BaseActivity<ActivityIntroBinding, BaseViewModel>() {
    override val layoutResID: Int = R.layout.activity_intro
    override val viewModel: IntroViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}