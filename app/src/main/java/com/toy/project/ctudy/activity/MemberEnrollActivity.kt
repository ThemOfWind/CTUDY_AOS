package com.toy.project.ctudy.activity

import android.os.Bundle
import com.toy.project.ctudy.BR
import com.toy.project.ctudy.R
import com.toy.project.ctudy.databinding.ActivityMemberEnrollBinding
import com.toy.project.ctudy.viewmodel.MemberEnrollViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * 멤버 등록 화면
 */
class MemberEnrollActivity : BaseActivity<ActivityMemberEnrollBinding, MemberEnrollViewModel>() {

    override val layoutResID: Int = R.layout.activity_member_enroll
    override val viewModel: MemberEnrollViewModel by viewModel()
    override val viewModelVariable: Int = BR.viewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}