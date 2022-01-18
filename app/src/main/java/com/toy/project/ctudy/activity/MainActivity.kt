package com.toy.project.ctudy.activity

import android.animation.ObjectAnimator
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import com.toy.project.ctudy.BR
import com.toy.project.ctudy.R
import com.toy.project.ctudy.databinding.ActivityMainBinding
import com.toy.project.ctudy.extension.singleStartActivity
import com.toy.project.ctudy.extension.startMoveActivity
import com.toy.project.ctudy.view.HeaderView
import com.toy.project.ctudy.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * 메인 페이지
 */
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    override val layoutResID = R.layout.activity_main
    override val viewModel: MainViewModel by viewModel()
    override val viewModelVariable: Int = BR.viewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(viewModel) {
            // 로그아웃
            loginState.observe(this@MainActivity, {
                if (it) {
                    singleStartActivity<LoginActivity>()
                }
            })

            // Floating Button
            // 메뉴 클릭 시
            menuFab.observe(this@MainActivity, {
                if (it) {
                    ObjectAnimator.ofFloat(viewBinding.mainAddFab, "translationY", 0f)
                        .apply { start() }
                    ObjectAnimator.ofFloat(viewBinding.mainLogoutFab, "translationY", 0f)
                        .apply { start() }
                } else {
                    // 버튼이 눌렸을 경우
                    ObjectAnimator.ofFloat(viewBinding.mainLogoutFab, "translationY", -400f)
                        .apply { start() }
                    ObjectAnimator.ofFloat(viewBinding.mainAddFab, "translationY", -200f)
                        .apply { start() }
                }
            })
            // 스터디 룸 등록 페이지 이동
            addRoomFab.observe(this@MainActivity, {
                startMoveActivity<RoomAddActivity>()
            })

        }

        viewBinding.headerView.setInitHeader(
            HeaderView.HEADER_BASIC,
            this@MainActivity.resources.getString(R.string.app_name),
            this@MainActivity
        )

        // Floating Button 이미지 색상 변경
        viewBinding.mainLogoutFab.imageTintList = ColorStateList.valueOf(Color.WHITE)
        viewBinding.mainAddFab.imageTintList = ColorStateList.valueOf(Color.WHITE)
        viewBinding.mainMenuFab.imageTintList = ColorStateList.valueOf(Color.WHITE)
    }
}