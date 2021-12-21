package com.toy.project.ctudy.repository.pref

/**
 * SharedPref Base Impl
 */
interface SharedPrefImpl {
    fun <T> setValue(key: String, value: T)
    fun getValue(key: String, value: String): String?
    fun getValue(key: String, value: Int): Int
    fun getValue(key: String, value: Boolean): Boolean
}