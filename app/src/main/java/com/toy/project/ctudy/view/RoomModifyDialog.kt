package com.toy.project.ctudy.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.toy.project.ctudy.R
import com.toy.project.ctudy.common.ResponseType
import com.toy.project.ctudy.databinding.RoomModifyDialogBinding
import com.toy.project.ctudy.interfaces.RoomModifyListener
import com.toy.project.ctudy.viewmodel.RoomModifyViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class RoomModifyDialog(id: String, master: String, modifyListener: RoomModifyListener) :
    DialogFragment() {
    lateinit var mRoomModifyDialogBinding: RoomModifyDialogBinding
    val mRoomModifyViewModel: RoomModifyViewModel by viewModel()

    val mRoomId = id
    val mMaster = master
    val mModifyListener = modifyListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        mRoomModifyDialogBinding = DataBindingUtil.inflate(LayoutInflater.from(context),
            R.layout.room_modify_dialog,
            null,
            false)
        initView()
        return mRoomModifyDialogBinding.root
    }

    fun initView() {
        with(mRoomModifyViewModel) {
            id.value = mRoomId
            master.value = mMaster

            // 수정 완료
            successModify.observe(this@RoomModifyDialog, { response ->
                if(response.equals(ResponseType.SUCCESS)){
                    dismiss()
                    mModifyListener.doneModify()
                }else{
                    Toast.makeText(context,
                        this@RoomModifyDialog.resources.getString(R.string.network_etc_error),
                        Toast.LENGTH_SHORT).show()
                }
            })

            isNameEmpty.observe(this@RoomModifyDialog, { result ->
                if (result) {
                    Toast.makeText(context,
                        this@RoomModifyDialog.resources.getString(R.string.study_room_modify_name_hint),
                        Toast.LENGTH_SHORT).show()
                }
            })

            mRoomModifyDialogBinding.modifyBtn.setOnClickListener(object : View.OnClickListener {
                override fun onClick(p0: View?) {
                    name.value = mRoomModifyDialogBinding.modifyNameEditText.text.toString()
                    roomModify()
                }
            })
        }

        mRoomModifyDialogBinding.modifyCloseBtn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                dismiss()
            }
        })
    }


}