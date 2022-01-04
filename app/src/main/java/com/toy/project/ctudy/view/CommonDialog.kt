package com.toy.project.ctudy.view

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatDialog
import com.toy.project.ctudy.R

class CommonDialog(context: Context) : AppCompatDialog(context) {
    val mContext = context

    init {
        setCancelable(false)
        setCanceledOnTouchOutside(false)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setContentView(R.layout.common_alert_dialog)
    }

    fun setContentMsg(msg: String) {

    }
}