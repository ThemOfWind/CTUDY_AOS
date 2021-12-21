package com.toy.project.ctudy.repository.pref

import android.content.SharedPreferences

/**
 * Base SharedPref
 * SharedPref 생성 -> Koin통해 single객체로 관리된다.
 */
abstract class BasePref(private val pref: SharedPreferences) : SharedPrefImpl {
    override fun <T> setValue(key: String, value: T) {
        when (value) {
            is String ->
                with(pref.edit()) {
                    putString(key, value)
                    apply()
                }
            is Int ->
                with(pref.edit()) {
                    putInt(key, value)
                    apply()
                }
            is Boolean ->
                with(pref.edit()) {
                    putBoolean(key, value)
                    apply()
                }
        }
    }

    override fun getValue(key: String, value: String): String? {
        return pref.getString(key, value)
    }

    override fun getValue(key: String, value: Int): Int {
        return pref.getInt(key, value)
    }

    override fun getValue(key: String, value: Boolean): Boolean {
        return pref.getBoolean(key, value)
    }
}