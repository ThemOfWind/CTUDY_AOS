package com.toy.project.ctudy.viewmodel

import com.toy.project.ctudy.repository.network.ApiService
import com.toy.project.ctudy.repository.pref.UserPref

/**
 * Login ViewModel
 */
class LoginViewModel(
    private val apiService: ApiService,
    private val userPref: UserPref,
) : BaseViewModel() {

    init {
        if (userPref.getLoginToken()!!.isNotEmpty()) {
            
        }
    }
}