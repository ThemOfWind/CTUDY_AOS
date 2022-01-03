package com.toy.project.ctudy.extension

import android.app.Activity
import android.content.Intent
import com.toy.project.ctudy.R

/**
 * Activity 이동 공통 정의
 *
 * inline + 고차함수 + 확장함수 적용하여 정의
 * 참고 : https://thdev.tech/k5otlin/2020/09/29/kotlin_effective_04/ (inline)
 * https://sungjk.github.io/2019/09/07/kotlin-reified.html (inline + reified)
 */
inline fun <reified T : Activity> Activity.singleStartActivity() {
    val intent = Intent(this, T::class.java)
    startActivity(intent)
    overridePendingTransition(R.anim.in_right_to_left, R.anim.in_right_to_left)
    finish()
}