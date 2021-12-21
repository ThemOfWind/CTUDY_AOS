package com.toy.project.ctudy.model.response

import com.google.gson.annotations.SerializedName

/**
 * Login Response
 */
data class LoginResponse(
    @SerializedName("access_token")
    val access_token: String? = "",
    @SerializedName("token_type")
    val token_type: String? = "",
    @SerializedName("refresh_token")
    val refresh_token: String? = ""
) : BaseResponse()
