package com.toy.project.ctudy.model.response

import com.google.gson.annotations.SerializedName

/**
 * Base Response
 */
open class BaseResponse {
    @SerializedName("result")
    val result: Boolean = false
}