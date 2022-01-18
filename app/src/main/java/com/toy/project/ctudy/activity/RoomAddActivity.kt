package com.toy.project.ctudy.activity

import android.os.Bundle
import com.toy.project.ctudy.BR
import com.toy.project.ctudy.R
import com.toy.project.ctudy.databinding.ActivityRoomAddBinding
import com.toy.project.ctudy.view.HeaderView
import com.toy.project.ctudy.viewmodel.RoomAddViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * 스터디 룸 등록 페이지
 */
class RoomAddActivity : BaseActivity<ActivityRoomAddBinding, RoomAddViewModel>() {
    override val layoutResID: Int = R.layout.activity_room_add
    override val viewModel: RoomAddViewModel by viewModel()
    override val viewModelVariable: Int = BR.viewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding.headerView.setInitHeader(
            HeaderView.HEADER_BACK,
            this@RoomAddActivity.resources.getString(R.string.enroll_study_room_text),
            this@RoomAddActivity
        )
    }
}