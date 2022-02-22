package com.toy.project.ctudy.activity

import android.os.Bundle
import com.toy.project.ctudy.BR
import com.toy.project.ctudy.R
import com.toy.project.ctudy.common.CommonDefine.ROOM_DETAIL_ID
import com.toy.project.ctudy.databinding.ActivityRoomDetailBinding
import com.toy.project.ctudy.viewmodel.RoomDetailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class RoomDetailActivity : BaseActivity<ActivityRoomDetailBinding, RoomDetailViewModel>() {
    override val layoutResID: Int = R.layout.activity_room_detail
    override val viewModel: RoomDetailViewModel by viewModel()
    override val viewModelVariable: Int = BR.viewModel

    private var mRoomId: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mRoomId = intent.getStringExtra(ROOM_DETAIL_ID)!!
        viewModel.getRoomDetail(mRoomId)

        with(viewModel){

        }
    }
}