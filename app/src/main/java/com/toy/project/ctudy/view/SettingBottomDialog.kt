package com.toy.project.ctudy.view

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.toy.project.ctudy.R
import com.toy.project.ctudy.common.CommonDefine
import com.toy.project.ctudy.common.ResponseType
import com.toy.project.ctudy.databinding.SettingBottomDialogBinding
import com.toy.project.ctudy.repository.etc.CommonDialogListener
import com.toy.project.ctudy.viewmodel.SettingBottomViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingBottomDialog(
    private val activity: Activity,
    private val intentId: String,
    private val roomModifyDialog: RoomModifyDialog,
    private val fragmentManagers: FragmentManager,
) :
    BottomSheetDialogFragment(), View.OnClickListener {

    lateinit var mBindingView: SettingBottomDialogBinding
    val mSettingViewModel: SettingBottomViewModel by viewModel()

    val mContext = this@SettingBottomDialog

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        mBindingView = SettingBottomDialogBinding.inflate(LayoutInflater.from(context))
        mBindingView.viewmodel = mSettingViewModel
        mBindingView.lifecycleOwner = this

        initView()

        return mBindingView.root
    }

    fun initView() {
        mBindingView.roodDetailDeleteText.setOnClickListener(this)
        mBindingView.roodDetailModifyText.setOnClickListener(this)

        with(mSettingViewModel) {
            id = intentId

            doModify.observe(mContext, {
                dialogDismiss()
                roomModifyDialog.show(fragmentManagers, "modifyDialog")
            })

            doDelete.observe(mContext, { response ->
                dialogDismiss()
                if (response.equals(ResponseType.SUCCESS)) {
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
                }else{
                    Toast.makeText(context,
                        this@SettingBottomDialog.resources.getString(R.string.network_etc_error),
                        Toast.LENGTH_SHORT).show()
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