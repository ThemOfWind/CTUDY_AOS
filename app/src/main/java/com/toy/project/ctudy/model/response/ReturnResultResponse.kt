package com.toy.project.ctudy.model.response

import com.google.gson.annotations.SerializedName

data class ReturnResultResponse(
    @SerializedName("result")
    val result: Boolean,
)
