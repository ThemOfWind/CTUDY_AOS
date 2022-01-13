package com.toy.project.ctudy.model.response

import com.google.gson.annotations.SerializedName

data class SignUpResponse(
    @SerializedName("username")
    val username: String,
    @SerializedName("name")
    val name: String,
) : BaseResponse()
