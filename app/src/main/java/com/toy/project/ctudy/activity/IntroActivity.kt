package com.toy.project.ctudy.activity

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.toy.project.ctudy.R
import com.toy.project.ctudy.common.FromIntroType
import com.toy.project.ctudy.databinding.ActivityIntroBinding
import com.toy.project.ctudy.extension.singleStartActivity
import com.toy.project.ctudy.viewmodel.IntroViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Intro Page
 */
class IntroActivity : BaseActivity<ActivityIntroBinding, IntroViewModel>() {
    override val layoutResID: Int = R.layout.activity_intro
    override val viewModel: IntroViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.startActivity.observe(this@IntroActivity, {
            Handler(Looper.getMainLooper()).postDelayed(object : Runnable {
                override fun run() {
                    when (it) {
                        FromIntroType.MOVE_LOGIN -> singleStartActivity<LoginActivity>()
                        FromIntroType.MOVE_MAIN -> singleStartActivity<MainActivity>()
                    }
                }
            }, 1000)
        })
    }
}