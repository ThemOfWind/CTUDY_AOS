package com.toy.project.ctudy.repository.network

import com.toy.project.ctudy.model.LoginData
import com.toy.project.ctudy.model.response.LoginResponse
import io.reactivex.Single
import retrofit2.http.POST

/**
 * Api 정의
 */
interface ApiService {
    @POST("/account/signin")
    fun login(loginData: LoginData): Single<LoginResponse>

}