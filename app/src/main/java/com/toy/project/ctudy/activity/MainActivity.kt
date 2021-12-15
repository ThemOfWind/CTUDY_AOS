package com.toy.project.ctudy.activity

import android.os.Bundle
import com.toy.project.ctudy.R
import com.toy.project.ctudy.databinding.ActivityMainBinding
import com.toy.project.ctudy.viewmodel.BaseViewModel
import com.toy.project.ctudy.viewmodel.MainViewModel

/**
 * Main Page
 */
class MainActivity : BaseActivity<ActivityMainBinding, BaseViewModel>() {
    override val layoutResID: Int = R.layout.activity_main
    override val viewModel: MainViewModel
        get() = TODO("Not yet implemented")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}