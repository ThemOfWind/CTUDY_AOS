package com.toy.project.ctudy.view

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.toy.project.ctudy.R
import com.toy.project.ctudy.common.CommonDefine
import com.toy.project.ctudy.databinding.SettingBottomDialogBinding
import com.toy.project.ctudy.repository.etc.CommonDialogListener
import com.toy.project.ctudy.viewmodel.RoomDetailViewModel

class SettingBottomDialog(
    private val viewModelFactory: ViewModelProvider.Factory,
    private val activity: Activity,
    private val intentId: String,
    private val roomModifyDialog: RoomModifyDialog,
) :
    BottomSheetDialogFragment(), View.OnClickListener {

    lateinit var mBindingView: SettingBottomDialogBinding
    lateinit var mSettingViewModel: RoomDetailViewModel

    val mContext = this@SettingBottomDialog

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        mBindingView = SettingBottomDialogBinding.inflate(LayoutInflater.from(context))
        mSettingViewModel =
            ViewModelProvider(this, viewModelFactory).get(RoomDetailViewModel::class.java)
        mBindingView.viewmodel = mSettingViewModel

        initView()

        return mBindingView.root
    }

    fun initView() {
        mBindingView.roodDetailDeleteText.setOnClickListener(this)
        mBindingView.roodDetailModifyText.setOnClickListener(this)

        with(mSettingViewModel) {
            id = intentId

            doModify.observe(mContext, {
                Log.d("눌렀니", "눌렀니")
            })

            doDelete.observe(mContext, {
                val msg =
                    mContext.resources.getString(R.string.study_room_delete_success)
                CommonDialog(
                    context = activity
                ).apply {
                    dialogClick(object : CommonDialogListener {
                        override fun onConfirm() {
                            dialogDismiss()
                            dismiss()
                            activity.setResult(CommonDefine.ROOM_REFRESH_REQUEST_CODE)
                            activity.finish()
                        }

                        override fun onCancle() {
                            dismiss()
                        }
                    })
                    setContentMsg(msg)
                    show()
                }
            })
        }
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            mBindingView.roodDetailDeleteText.id ->
                CommonDialog(
                    context = activity
                ).apply {
                    val msg =
                        mContext.resources.getString(R.string.study_room_delete)
                    dialogClick(object : CommonDialogListener {
                        override fun onConfirm() {
                            dialogDismiss()
                            dismiss()
                            mSettingViewModel.roomDelete()
                        }

                        override fun onCancle() {
                            dismiss()
                        }
                    })
                    setContentMsg(msg)
                    show()
                }
        }
    }


    fun dialogDismiss() {
        if (isVisible) {
            dismiss()
        }
    }
}