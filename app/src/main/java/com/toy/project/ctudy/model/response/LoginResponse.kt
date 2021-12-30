package com.toy.project.ctudy.model.response

import com.google.gson.annotations.SerializedName

/**
 * Login Response
 */
data class LoginResponse(
    @SerializedName("response")
    val response: LoginInfo,
) : BaseResponse()

data class LoginInfo(
    @SerializedName("access_token")
    val access_token: String,
    @SerializedName("token_type")
    val token_type: String,
    @SerializedName("refresh_token")
    val refresh_token: String,
    @SerializedName("expires_in")
    val expires_in: Int,
    @SerializedName("scope")
    val scope: String,
)