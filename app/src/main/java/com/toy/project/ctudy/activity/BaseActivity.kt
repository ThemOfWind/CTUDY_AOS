package com.toy.project.ctudy.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewbinding.ViewBinding
import com.toy.project.ctudy.viewmodel.BaseViewModel

abstract class BaseActivity<T : ViewBinding, R : BaseViewModel> : AppCompatActivity() {

    lateinit var viewBinding: T
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}