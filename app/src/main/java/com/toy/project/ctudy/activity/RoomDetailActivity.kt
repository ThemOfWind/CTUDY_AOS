package com.toy.project.ctudy.activity

import android.os.Bundle
import com.toy.project.ctudy.BR
import com.toy.project.ctudy.R
import com.toy.project.ctudy.common.CommonDefine.ROOM_DETAIL_ID
import com.toy.project.ctudy.databinding.ActivityRoomDetailBinding
import com.toy.project.ctudy.view.RoomModifyDialog
import com.toy.project.ctudy.viewmodel.RoomDetailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class RoomDetailActivity : BaseActivity<ActivityRoomDetailBinding, RoomDetailViewModel>() {
    override val layoutResID: Int = R.layout.activity_room_detail
    override val viewModel: RoomDetailViewModel by viewModel()
    override val viewModelVariable: Int = BR.viewModel

    private var mRoomId: String = ""
    lateinit var mRoomModifyDialog: RoomModifyDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mRoomId = intent.getStringExtra(ROOM_DETAIL_ID)!!

        with(viewModel) {
            id = mRoomId
            // 상세 룸정보 호출
            getRoomDetail()

            doModify.observe(this@RoomDetailActivity, {
                mRoomModifyDialog = RoomModifyDialog(this@RoomDetailActivity)
                mRoomModifyDialog.show()
            })
        }
    }
}