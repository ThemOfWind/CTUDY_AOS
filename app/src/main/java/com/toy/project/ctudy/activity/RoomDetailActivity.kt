package com.toy.project.ctudy.activity

import android.os.Bundle
import android.view.View
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.toy.project.ctudy.BR
import com.toy.project.ctudy.R
import com.toy.project.ctudy.common.AlertDialogBtnType
import com.toy.project.ctudy.common.CommonDefine
import com.toy.project.ctudy.common.CommonDefine.ROOM_DETAIL_ID
import com.toy.project.ctudy.common.CommonDefine.ROOM_DETAIL_MASTER
import com.toy.project.ctudy.databinding.ActivityRoomDetailBinding
import com.toy.project.ctudy.interfaces.RoomModifyListener
import com.toy.project.ctudy.repository.etc.CommonDialogListener
import com.toy.project.ctudy.view.HeaderView
import com.toy.project.ctudy.view.RoomModifyDialog
import com.toy.project.ctudy.view.SettingBottomDialog
import com.toy.project.ctudy.viewmodel.RoomDetailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class RoomDetailActivity : BaseActivity<ActivityRoomDetailBinding, RoomDetailViewModel>(),
    View.OnClickListener {
    override val layoutResID: Int = R.layout.activity_room_detail
    override val viewModel: RoomDetailViewModel by viewModel()
    override val viewModelVariable: Int = BR.viewModel

    private var mRoomId: String = ""
    private var mRoomMaster: String = ""
    lateinit var mRoomModifyDialog: RoomModifyDialog
    lateinit var bottomSheetDialog: BottomSheetDialog

    lateinit var bottomSheetDialogFragment: BottomSheetDialogFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mRoomId = intent.getStringExtra(ROOM_DETAIL_ID)!!
        mRoomMaster = intent.getStringExtra(ROOM_DETAIL_MASTER)!!
        mRoomModifyDialog = RoomModifyDialog(mRoomId, mRoomMaster, mModifyListener())

        bottomSheetDialogFragment =
            SettingBottomDialog(viewModelFactory = viewModel.viewModelFactory(),
                this,
                mRoomId,
                mRoomModifyDialog)

        with(viewModel) {
            id = mRoomId

            // 상세 룸정보 호출
            getRoomDetail()
        }

        viewBinding.headerView.setInitHeader(HeaderView.HEADER_BACK, this)
        viewBinding.settingIcon.setOnClickListener(this)
    }

    fun mModifyListener() = object : RoomModifyListener {
        override fun doneModify() {
            showCommonDialog(AlertDialogBtnType.ONE,
                this@RoomDetailActivity.resources.getString(R.string.study_room_modify_success_text))
                .apply {
                    dialogClick(object : CommonDialogListener {
                        override fun onConfirm() {
                            BackToRefreshRoomInfo()
                        }

                        override fun onCancle() {
                            dismiss()
                        }
                    })
                }

        }
    }

    fun BackToRefreshRoomInfo() {
        setResult(CommonDefine.ROOM_REFRESH_REQUEST_CODE)
        finish()
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    override fun onDestroy() {
        super.onDestroy()

        mRoomModifyDialog.onDestroy()
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            viewBinding.settingIcon.id ->
                if (!bottomSheetDialogFragment.isVisible) {
                    bottomSheetDialogFragment.show(supportFragmentManager, "Setting Menu")
                }
        }
    }
}