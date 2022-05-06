package com.toy.project.ctudy.common

import androidx.annotation.StringRes
import com.toy.project.ctudy.R

/**
 * 각 Type 별 Const 정의 Enum Class
 * Enum Class ( 상수를 나열할 수 있는 열거형 클래스 )
 */
enum class ResponseType {
    SUCCESS,
    FAIL
}

enum class FromIntroType {
    MOVE_MAIN,
    MOVE_LOGIN
}

enum class LoadingDialogType {
    SHOW,
    DISMISS
}

enum class NetWorkDialogType(@StringRes val msg: Int) {
    NETWORK_ERROR(msg = R.string.network_error),
    ETC_ERROR(msg = R.string.network_etc_error),
}

enum class AlertDialogBtnType {
    ONE
}

enum class LoginEditErrorType(@StringRes val msg: Int) {
    EMPTY_ID(msg = R.string.login_empty_id),
    EMPTY_PASSWORD(msg = R.string.login_empty_password),
    VARIFICATION_ID(msg = R.string.login_verification_id)
}

enum class SignUpResponseType() {
    EMPTY_ID,
    EMPTY_PASSWORD,
    EMPTY_NAME,
    CONFIRM_ID,
    CONFIRM_PASSWORD,
    DUPLE_ID,
    DO_SIGNUP,
    FAIL_SIGNUP
}