package com.toy.project.ctudy.view

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatDialog
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.toy.project.ctudy.R
import com.toy.project.ctudy.extension.CommonFunction

/**
 * 공통 Loading View
 */
class LoadingDialog(context: Context) : AppCompatDialog(context) {

    var mContext: Context = context

    init {
        setCancelable(false)
        setCanceledOnTouchOutside(false)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setContentView(R.layout.common_loading)

        var loadingImg = findViewById<AppCompatImageView>(R.id.loading_img)

        CommonFunction.setBgImageView(mContext, R.drawable.loading_dialog, loadingImg!!)
    }
}