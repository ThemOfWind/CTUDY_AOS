package com.toy.project.ctudy.activity

import android.os.Bundle
import com.toy.project.ctudy.R
import com.toy.project.ctudy.databinding.ActivityMainBinding
import com.toy.project.ctudy.viewmodel.BaseViewModel
import com.toy.project.ctudy.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Main Page
 */
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    override val layoutResID: Int = R.layout.activity_main
    override val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}