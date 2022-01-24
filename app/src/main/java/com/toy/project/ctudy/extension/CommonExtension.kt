package com.toy.project.ctudy.extension

import com.toy.project.ctudy.viewmodel.BaseViewModel
import java.util.regex.Pattern

/**
 * 공통 Inline 사용 함수 정의
 */

/**
 * 이메일 형식 검사
 * 정규식 사용
 * @id : 이메일
 */
inline fun BaseViewModel.varifiacateLoginId(id: String): Boolean {
    val emailReg = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";
    val pattern = Pattern.compile(emailReg)
    val matcher = pattern.matcher(id)
    if (matcher.matches())
        return true
    return false
}
