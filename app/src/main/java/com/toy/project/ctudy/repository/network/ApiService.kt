package com.toy.project.ctudy.repository.network

import com.toy.project.ctudy.common.HttpDefine
import com.toy.project.ctudy.model.LoginData
import com.toy.project.ctudy.model.response.BaseResponse
import com.toy.project.ctudy.model.response.LoginResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

/**
 * Api 정의
 */
interface ApiService {
    @POST(HttpDefine.CTUDY_API + "/account/signin/")
    fun login(@Body loginData: LoginData): Single<LoginResponse>

    @GET(HttpDefine.CTUDY_API + "/account/logout/")
    fun logout(@Header("Authorization") authorization: String): Single<BaseResponse>
}