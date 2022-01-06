package com.toy.project.ctudy.common

/**
 * 각 Type 별 Const 정의 Enum Class
 * Enum Class ( 상수를 나열할 수 있는 열거형 클래스 )
 */
enum class FromIntroType {
    MOVE_MAIN,
    MOVE_LOGIN
}

enum class LoadingDialogType {
    SHOW,
    DISMISS
}

enum class NetWorkDialogType(val msg: String) {
    NETWORK_ERROR(msg = "통신 중 에러가 발생하였습니다. \n 잠시후 재시도 해주세요"),
    ETC_ERROR(msg = "에러가 발생하였습니다. \n 잠시후 재시도 해주세요"),
}

enum class AlertDialogBtnType {
    ONE,
}