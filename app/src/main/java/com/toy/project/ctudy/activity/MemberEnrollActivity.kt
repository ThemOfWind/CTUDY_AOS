package com.toy.project.ctudy.activity

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.toy.project.ctudy.BR
import com.toy.project.ctudy.R
import com.toy.project.ctudy.adapter.MemberListAdapter
import com.toy.project.ctudy.common.AlertDialogBtnType
import com.toy.project.ctudy.common.CommonDefine
import com.toy.project.ctudy.databinding.ActivityMemberEnrollBinding
import com.toy.project.ctudy.model.response.MemberList
import com.toy.project.ctudy.repository.etc.CommonDialogListener
import com.toy.project.ctudy.repository.etc.RecyclerBottomView
import com.toy.project.ctudy.viewmodel.MemberEnrollViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * 멤버 등록 화면
 */
class MemberEnrollActivity : BaseActivity<ActivityMemberEnrollBinding, MemberEnrollViewModel>() {

    override val layoutResID: Int = R.layout.activity_member_enroll
    override val viewModel: MemberEnrollViewModel by viewModel()
    override val viewModelVariable: Int = BR.viewModel

    private val mRecyclerBottomView: RecyclerBottomView by inject()
    private var mRoomName: String = ""
    private var mMemberList = ArrayList<MemberList>()

    lateinit var mMemberEnrollAdapter: MemberListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mRoomName = intent.getStringExtra(CommonDefine.ROOM_ADD_NAME)!!

        with(viewModel) {
            roomName = mRoomName

            memberList.observe(this@MemberEnrollActivity, { list ->
                if (list.size > 0) {
                    viewBinding.memberEnrollRecycler.apply {
                        layoutManager = LinearLayoutManager(context)
                        mMemberEnrollAdapter = MemberListAdapter(list)
                        adapter = mMemberEnrollAdapter
                        addItemDecoration(mRecyclerBottomView)
                        setHasFixedSize(false)
                        mMemberList.addAll(list)
                    }
                }
            })

            enrollRoomState.observe(this@MemberEnrollActivity, {
                if (it)
                    showCommonDialog(
                        AlertDialogBtnType.ONE,
                        this@MemberEnrollActivity.resources.getString(R.string.enroll_study_room_success))
                        .let {
                            it.dialogClick(object : CommonDialogListener {
                                override fun onConfirm() {
                                    setResult(CommonDefine.ROOM_ADD_COMPLETE_CODE)
                                    finish()
                                }

                                override fun onCancle() {
                                    it.dismiss()
                                }
                            })
                        }
                else
                    showCommonDialog(
                        AlertDialogBtnType.ONE,
                        this@MemberEnrollActivity.resources.getString(R.string.enroll_study_room_fail))
            })
        }
    }
}