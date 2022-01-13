package com.toy.project.ctudy.model.response

import com.google.gson.annotations.SerializedName

data class DupleChkResponse(
    @SerializedName("username")
    val username: String,
) : BaseResponse()