package com.toy.project.ctudy.repository.pref

import android.content.SharedPreferences

/**
 * 사용자 관련 Pref
 */
class UserPref(pref: SharedPreferences) : BasePref(pref) {
    private val USER_LOGIN_ID = "login_id"
    private val USER_LOGIN_TOKEN = "login_token"

    fun setLoginId(id: String) {
        setValue(USER_LOGIN_ID, id)
    }

    fun setLoginToken(token: String) {
        setValue(USER_LOGIN_TOKEN, token)
    }

    fun getLoginId(): String? {
        return getValue(USER_LOGIN_ID, "")
    }

    fun getLoginToken(): String? {
        return getValue(USER_LOGIN_TOKEN, "")
    }
}