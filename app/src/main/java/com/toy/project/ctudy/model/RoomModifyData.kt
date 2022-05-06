package com.toy.project.ctudy.model

import com.google.gson.annotations.SerializedName

data class RoomModifyData(
    @SerializedName("name")
    val name: String,
    @SerializedName("master")
    val master: String? = null,
)
