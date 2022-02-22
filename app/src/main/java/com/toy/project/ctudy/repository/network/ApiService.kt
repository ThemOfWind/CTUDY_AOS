package com.toy.project.ctudy.repository.network

import com.toy.project.ctudy.common.HttpDefine
import com.toy.project.ctudy.model.LoginData
import com.toy.project.ctudy.model.RoomEnrollData
import com.toy.project.ctudy.model.SignUpData
import com.toy.project.ctudy.model.response.*
import io.reactivex.Single
import retrofit2.http.*

/**
 * Api 정의
 */
interface ApiService {
    @POST(HttpDefine.CTUDY_API + "/account/signin/")
    fun login(@Body loginData: LoginData): Single<LoginResponse>

    @GET(HttpDefine.CTUDY_API + "/account/logout/")
    fun logout(): Single<BaseResponse>

    @POST(HttpDefine.CTUDY_API + "/account/signup/")
    fun signUp(@Body signUpData: SignUpData): Single<SignUpResponse>

    @GET(HttpDefine.CTUDY_API + "/account/signup/")
    fun dupleIdConfirm(@Query("username") username: String): Single<DupleChkResponse>

    @POST(HttpDefine.CTUDY_API + "/study/room/")
    fun studyRoomEnroll(@Body roomEnrollData: RoomEnrollData): Single<BaseResponse>

    @GET(HttpDefine.CTUDY_API + "/study/room/")
    fun studyAllRoomInquiry(): Single<RoomAllResponse>

    @GET(HttpDefine.CTUDY_API + "/study/room/{id}")
    fun studyRoomDetail(@Path("id") id: String): Single<RoomDetailResponse>
}