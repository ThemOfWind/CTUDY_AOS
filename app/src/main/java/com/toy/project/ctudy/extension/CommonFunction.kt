package com.toy.project.ctudy.extension

import android.content.Context
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide

/**
 * 공통 사용 함수 정의
 * object 선언 / 참고 : https://www.bsidesoft.com/8187
 */
object CommonFunction {
    fun setBgImageView(context: Context, resId: Int, imageView: AppCompatImageView) {
        Glide.with(context)
            .load(resId)
            .into(imageView)
    }

    fun setImageView(context: Context, resId: String, imageView: AppCompatImageView) {
        Glide.with(context)
            .load(resId)
            .into(imageView)
    }
}