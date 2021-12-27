package com.toy.project.ctudy.viewmodel

import androidx.lifecycle.LiveData
import com.toy.project.ctudy.common.FromIntroType
import com.toy.project.ctudy.common.SingleLiveEvent
import com.toy.project.ctudy.repository.pref.UserPref

/**
 * Intro ViewModel
 */
class IntroViewModel(
    private val userPref: UserPref,
) : BaseViewModel() {

    val startActivity = SingleLiveEvent<FromIntroType>()

    init {
        if (userPref.getAccessToken().isNullOrEmpty()) {
            startActivity.postValue(FromIntroType.MOVE_LOGIN)
        } else {
            startActivity.postValue(FromIntroType.MOVE_MAIN)
        }

        startActivity.call()
    }
}