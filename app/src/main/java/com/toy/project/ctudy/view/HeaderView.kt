package com.toy.project.ctudy.view

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.view.isVisible
import com.google.android.material.textview.MaterialTextView
import com.toy.project.ctudy.R

/**
 * Header View
 */
class HeaderView : LinearLayout {
    lateinit var mHeaderView: View
    lateinit var mBackActivity: Activity

    private var mContext = context

    var mHeaderBack: AppCompatImageView? = null
    var mHeaderTitle: MaterialTextView? = null

    constructor(context: Context) : super(context) {
        initView(context)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initView(context)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context,
        attrs,
        defStyleAttr) {
        initView(context)
    }

    // 헤더 타입 명시
    companion object {
        val HEADER_BASIC = "basic"
        val HEADER_BACK = "back"
    }

    fun initView(context: Context) {
        mContext = context
        val layoutInflator: LayoutInflater =
            mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        mHeaderView = layoutInflator.inflate(R.layout.header_layout, this, false)
        addView(mHeaderView)

        mHeaderBack = findViewById(R.id.header_back)
        mHeaderTitle = findViewById(R.id.header_title)

        mHeaderBack?.setOnClickListener(object : OnClickListener {
            override fun onClick(p0: View?) {
                mBackActivity.finish()
                mBackActivity.overridePendingTransition(R.anim.in_left_to_right,
                    R.anim.in_left_to_right)
            }
        })
    }

    // 헤더 타입에 따라 Visible 처리
    fun setInitHeader(type: String, activity: Activity) {
        when (type) {
            HEADER_BASIC -> mHeaderBack?.isVisible = false
            HEADER_BACK -> mHeaderBack?.isVisible = true
        }
        mBackActivity = activity
    }
}