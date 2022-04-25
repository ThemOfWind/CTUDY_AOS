package com.toy.project.ctudy.activity

import android.content.Context
import android.os.Bundle
import androidx.core.view.isVisible
import com.toy.project.ctudy.BR
import com.toy.project.ctudy.R
import com.toy.project.ctudy.common.AlertDialogBtnType
import com.toy.project.ctudy.common.SignUpResponseType
import com.toy.project.ctudy.databinding.ActivitySignBinding
import com.toy.project.ctudy.repository.etc.CommonDialogListener
import com.toy.project.ctudy.view.HeaderView
import com.toy.project.ctudy.viewmodel.SignViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * 회원가입 페이지
 */
class SignActivity : BaseActivity<ActivitySignBinding, SignViewModel>() {
    override val layoutResID: Int = R.layout.activity_sign
    override val viewModel: SignViewModel by viewModel()
    override val viewModelVariable: Int = BR.viewModel
    private var mContext: Context? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mContext = this@SignActivity
        with(viewModel) {
            completeSignOut.observe(this@SignActivity, {
                setInitErrorText()

                if (it == SignUpResponseType.EMPTY_ID) {
                    viewBinding.errorEmptyIdText.isVisible = true
                } else if (it == SignUpResponseType.EMPTY_PASSWORD) {
                    viewBinding.errorEmptyPasswordText.isVisible = true
                } else if (it == SignUpResponseType.EMPTY_NAME) {
                    viewBinding.errorEmptyNameText.isVisible = true
                } else if (it == SignUpResponseType.CONFIRM_ID) {
                    viewBinding.errorVerificateIdText.isVisible = true
                } else if (it == SignUpResponseType.CONFIRM_PASSWORD) {
                    viewBinding.errorConfirmPasswordText.isVisible = true
                } else if (it == SignUpResponseType.DUPLE_ID) {
                    viewBinding.errorDupleConfirmIdText.isVisible = true
                } else if (it == SignUpResponseType.DO_SIGNUP) {
                    showCommonDialog(AlertDialogBtnType.ONE,
                        this@SignActivity.resources.getString(R.string.sign_in_success))
                        .let {
                            it.dialogClick(object : CommonDialogListener {
                                override fun onConfirm() {
                                    finish()
                                }

                                override fun onCancle() {
                                    it.dismiss()
                                }

                            })
                        }

                } else {
                    showCommonDialog(AlertDialogBtnType.ONE,
                        this@SignActivity.resources.getString(R.string.sign_in_fail))
                }
            })


            dupleCheck.observe(this@SignActivity, {
                if (it) {
                    // 사용가능한 아이디
                    showCommonDialog(AlertDialogBtnType.ONE,
                        this@SignActivity.resources.getString(R.string.sign_in_duple_confirm_success))
                } else {
                    // 중복된 아이디 존재
                    showCommonDialog(AlertDialogBtnType.ONE,
                        this@SignActivity.resources.getString(R.string.sign_in_duple_confirm_fail))
                }
                viewBinding.errorEmptyIdText.isVisible = false
                viewBinding.errorDupleConfirmIdText.isVisible = false
                viewBinding.errorVerificateIdText.isVisible = false
            })
        }
        viewBinding.headerView.setInitHeader(
            HeaderView.HEADER_BACK,
            this@SignActivity
        )
    }

    fun setInitErrorText() {
        viewBinding.errorConfirmPasswordText.isVisible = false
        viewBinding.errorDupleConfirmIdText.isVisible = false
        viewBinding.errorEmptyIdText.isVisible = false
        viewBinding.errorEmptyNameText.isVisible = false
        viewBinding.errorVerificateIdText.isVisible = false
        viewBinding.errorEmptyPasswordText.isVisible = false
    }
}