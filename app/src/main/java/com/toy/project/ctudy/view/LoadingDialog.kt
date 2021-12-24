package com.toy.project.ctudy.view

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.LayoutInflater
import android.view.Window
import com.bumptech.glide.Glide
import com.toy.project.ctudy.R
import com.toy.project.ctudy.databinding.CommonLoadingBinding

/**
 * 공통 Loading View
 */
class LoadingDialog(context: Context) : AlertDialog(context) {

    lateinit var mBindingView: CommonLoadingBinding
    lateinit var mAlertDialogs: AlertDialog
    lateinit var mActivity: Activity
    var mContext: Context = context


    init {
        onCreate()
    }

    fun onCreate() {
        mActivity = mContext as Activity
        val factory = LayoutInflater.from(mActivity)
        val view = factory.inflate(R.layout.common_loading, null)
        mAlertDialogs = Builder(mActivity).create()
        mBindingView = CommonLoadingBinding.inflate(LayoutInflater.from(mContext),
            null, false)


        Glide.with(mContext).load(R.drawable.loading_dialog).into(mBindingView.loadingImg)


        mAlertDialogs.let {
            requestWindowFeature(Window.FEATURE_NO_TITLE)

            it.window.let {
                it?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                it?.setGravity(Gravity.CENTER)
            }

            setView(view)
            setCanceledOnTouchOutside(false)
            setCancelable(false)
        }

    }


    fun dismissDialog() {
        mActivity.let {
            if (!it.isFinishing) {
                mAlertDialogs.let {
                    dismiss()
                }
            }
        }
    }

    fun showDialog() {
        mActivity.let {
            if (!it.isFinishing) {
                mAlertDialogs.let {
                    if (!it.isShowing) {
                        show()
                    }
                }
            }
        }
    }
}