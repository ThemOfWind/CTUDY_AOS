package com.toy.project.ctudy.extension

import android.content.Context
import android.graphics.Outline
import android.graphics.drawable.GradientDrawable
import android.view.View
import android.view.ViewOutlineProvider
import android.widget.ImageView
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.toy.project.ctudy.R

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

    /**
     * 상단 corner 이미지뷰 세팅
     */
    fun setRoundImageView(context: Context, resId: String, imageView: ImageView) {
        imageView.outlineProvider = object : ViewOutlineProvider() {
            override fun getOutline(view: View, outline: Outline) {
                val left = 0
                val top = 0
                val right: Int = view.getWidth()
                val bottom: Int = view.getHeight()
                val cornerRadius = 60
                outline.setRoundRect(left,
                    top,
                    right,
                    bottom + cornerRadius,
                    cornerRadius.toFloat())
            }
        }

        imageView.clipToOutline = true

        Glide.with(context)
            .load(resId)
            .into(imageView)
    }
}