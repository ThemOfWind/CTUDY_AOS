package com.toy.project.ctudy.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.toy.project.ctudy.R
import com.toy.project.ctudy.databinding.HeaderLayoutBinding

/**
 * Header View
 */
class HeaderView(context: Context) : ConstraintLayout(context) {
    lateinit var mHeaderView: View
    private val mBindingView: HeaderLayoutBinding =
        HeaderLayoutBinding.inflate(LayoutInflater.from(context), this, false)
    private val mContext = context

    // 헤더 타입 명시
    companion object {
        val HEADER_BASIC = "basic"
        val HEADER_BACK = "back"
    }

    init {
        initView()
    }

    fun initView() {
        val layoutInflator: LayoutInflater =
            mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        mHeaderView = layoutInflator.inflate(R.layout.header_layout, this, false)
        addView(mHeaderView)
    }

    // 헤더 타입에 따라 Visible 처리
    fun setHeaderViewType(type: String) {
        when (type) {
            HEADER_BASIC -> mBindingView.headerBack.isVisible = false
            HEADER_BACK -> mBindingView.headerBack.isVisible = true
        }
    }
}