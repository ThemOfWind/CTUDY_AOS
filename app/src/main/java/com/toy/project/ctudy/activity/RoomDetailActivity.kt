package com.toy.project.ctudy.activity

import android.os.Bundle
import android.util.Log
import android.view.View
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
import com.toy.project.ctudy.viewmodel.RoomDetailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class RoomDetailActivity : BaseActivity<ActivityRoomDetailBinding, RoomDetailViewModel>() {
    override val layoutResID: Int = R.layout.activity_room_detail
    override val viewModel: RoomDetailViewModel by viewModel()
    override val viewModelVariable: Int = BR.viewModel

    private var mRoomId: String = ""
    private var mRoomMaster: String = ""
    lateinit var mRoomModifyDialog: RoomModifyDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mRoomId = intent.getStringExtra(ROOM_DETAIL_ID)!!
        mRoomMaster = intent.getStringExtra(ROOM_DETAIL_MASTER)!!
        mRoomModifyDialog = RoomModifyDialog(mRoomId, mRoomMaster, mModifyListener())

        with(viewModel) {
            id = mRoomId
            // 상세 룸정보 호출
            getRoomDetail()

            doModify.observe(this@RoomDetailActivity, {
                mRoomModifyDialog.show(supportFragmentManager, "ModifyDialog")
            })

            doDelete.observe(this@RoomDetailActivity, {
                showCommonDialog(AlertDialogBtnType.ONE,
                    this@RoomDetailActivity.resources.getString(R.string.study_room_delete_success)
                ).apply {
                    dialogClick(object : CommonDialogListener {
                        override fun onConfirm() {
                            BackToRefreshRoomInfo()
                        }

                        override fun onCancle() {
                            dismiss()
                        }
                    })
                }
            })
            viewBinding.roomDetailDeleteLayout.setOnClickListener(object : View.OnClickListener {
                override fun onClick(p0: View?) {
                    showCommonDialog(AlertDialogBtnType.ONE,
                        this@RoomDetailActivity.resources.getString(R.string.study_room_delete)
                    ).apply {
                        dialogClick(object : CommonDialogListener {
                            override fun onConfirm() {
                                roomDelete()
                            }

                            override fun onCancle() {
                                dismiss()
                            }
                        })
                    }

                }
            })
        }

        viewBinding.headerView.setInitHeader(HeaderView.HEADER_BACK, this)
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
}