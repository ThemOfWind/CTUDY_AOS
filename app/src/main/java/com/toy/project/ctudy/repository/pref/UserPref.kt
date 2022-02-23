package com.toy.project.ctudy.repository.pref

import android.content.SharedPreferences

/**
 * 사용자 관련 Pref
 */
class UserPref(pref: SharedPreferences) : BasePref(pref) {
    private val USER_LOGIN_ID = "login_id"
    private val USER_LOGIN_ACCESS_TOKEN = "login_access_token"
    private val USER_LOGIN_TOKEN_TYPE = "login_token_type"
    private val USER_LOGIN_REFRESH_TOKEN = "login_refresh_token"
    private val USER_AUTO_LOGIN = "login_auto_login"
    private val USER_BEAR_TOKEN = "login_bear_token"

    fun setLoginId(id: String?) {
        setValue(USER_LOGIN_ID, id)
    }

    fun setAccessToken(token: String?) {
        setValue(USER_LOGIN_ACCESS_TOKEN, token)
    }

    fun setTokenType(tokenType: String?) {
        setValue(USER_LOGIN_TOKEN_TYPE, tokenType)
    }

    fun setRefreshToken(refreshToken: String?) {
        setValue(USER_LOGIN_REFRESH_TOKEN, refreshToken)
    }

    fun getLoginId(): String? {
        return getValue(USER_LOGIN_ID, "")
    }

    fun getAccessToken(): String? {
        return getValue(USER_LOGIN_ACCESS_TOKEN, "")
    }

    fun getTokenType(): String? {
        return getValue(USER_LOGIN_TOKEN_TYPE, "")
    }

    fun getRefreshToken(refreshToken: String): String? {
        return getValue(USER_LOGIN_REFRESH_TOKEN, "")
    }

    fun setAutoLogin(autoLogin: Boolean) {
        setValue(USER_AUTO_LOGIN, autoLogin)
    }

    fun getAutoLogin(): Boolean {
        return getValue(USER_AUTO_LOGIN, false)
    }

    fun setAuthorizationToken(bearToken : String) {
        setValue(USER_BEAR_TOKEN, bearToken)
    }

    fun getAuthorizationToken(): String? {
        return getValue(USER_BEAR_TOKEN, "")
    }

    fun getLoginStatus(): Boolean {
        if (getAccessToken().isNullOrEmpty()) {
            return false;
        }
        return true
    }
}