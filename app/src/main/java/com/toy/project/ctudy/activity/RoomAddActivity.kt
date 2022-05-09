package com.toy.project.ctudy.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.toy.project.ctudy.BR
import com.toy.project.ctudy.R
import com.toy.project.ctudy.common.CommonDefine
import com.toy.project.ctudy.common.CommonDefine.ROOM_REFRESH_REQUEST_CODE
import com.toy.project.ctudy.databinding.ActivityRoomAddBinding
import com.toy.project.ctudy.extension.moveMemeberEnrollActivity
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

    private lateinit var mResult: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mResult = activityForResult()

        viewBinding.headerView.setInitHeader(
            HeaderView.HEADER_BACK,
            this@RoomAddActivity
        )

        with(viewModel) {
            addNext.observe(this@RoomAddActivity, {
                if (roomName.value.isNullOrEmpty()) {
                    showToastMessage(this@RoomAddActivity.resources.getString(R.string.study_room_modify_name_hint))
                } else {
                    // 멤버 등록 페이지 이동
                    moveMemeberEnrollActivity<MemberEnrollActivity>(roomName.toString(), mResult)
                }
            })
        }
    }

    fun activityForResult(): ActivityResultLauncher<Intent> {
        return registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == CommonDefine.ROOM_ADD_COMPLETE_CODE) {
                setResult(ROOM_REFRESH_REQUEST_CODE)
                finish()
            }
        }
    }
}