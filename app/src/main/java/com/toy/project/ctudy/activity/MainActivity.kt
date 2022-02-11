package com.toy.project.ctudy.activity

import android.animation.ObjectAnimator
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.toy.project.ctudy.BR
import com.toy.project.ctudy.R
import com.toy.project.ctudy.adapter.MainRoomAdapter
import com.toy.project.ctudy.common.CommonDefine.ROOM_ADD_REQUEST_CODE
import com.toy.project.ctudy.databinding.ActivityMainBinding
import com.toy.project.ctudy.extension.singleStartActivity
import com.toy.project.ctudy.extension.startMoveActivity
import com.toy.project.ctudy.extension.startMoveResultActivity
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

        // Floating Button 이미지 색상 변경
        viewBinding.mainLogoutFab.imageTintList = ColorStateList.valueOf(Color.WHITE)
        viewBinding.mainAddFab.imageTintList = ColorStateList.valueOf(Color.WHITE)
        viewBinding.mainMenuFab.imageTintList = ColorStateList.valueOf(Color.WHITE)

        // 헤더 세팅
        viewBinding.headerView.setInitHeader(
            HeaderView.HEADER_BASIC,
            this@MainActivity.resources.getString(R.string.app_name),
            this@MainActivity
        )

        /**
         * viewModel Observe 정의
         */
        with(viewModel) {
            mainRoomList.observe(this@MainActivity, {
                Log.d("로그 :: 리스트사이즈", it.size.toString())
                val mainRoomAdapter = MainRoomAdapter(it)
                viewBinding.roomRecyclerView.apply {
                    layoutManager = LinearLayoutManager(context)
                    adapter = mainRoomAdapter
                    setHasFixedSize(false)
                }
            })

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
                startMoveResultActivity<RoomAddActivity>(ROOM_ADD_REQUEST_CODE)
                // TODO 액티비티 이동 후 종료 시 값 보내는 방식 수정 'registerForActivityResult' 사용
                // 참고 : https://developer88.tistory.com/351
                // https://onedaythreecoding.tistory.com/entry/Android-%EB%8B%A4%EB%A5%B8-%EC%95%A1%ED%8B%B0%EB%B9%84%ED%8B%B0-%EC%8B%A4%ED%96%89-%ED%9B%84-%EA%B2%B0%EA%B3%BC-%EB%B0%9B%EC%95%84%EC%98%A4%EA%B8%B0-startActivityForResult-setRestult-%EA%B5%AC-onActivityResult
            })
        }
    }
}