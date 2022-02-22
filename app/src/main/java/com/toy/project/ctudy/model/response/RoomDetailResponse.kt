package com.toy.project.ctudy.model.response

import com.google.gson.annotations.SerializedName

data class RoomDetailResponse(
    @SerializedName("response")
    val response: RoomDetailData,
) : BaseResponse()

data class RoomDetailData(
    @SerializedName("name")
    val name: String,
    @SerializedName("members")
    val members: ArrayList<RoomMemberDetailData>,
    @SerializedName("master")
    val master: RoomMemberDetailData,
)

data class RoomMemberDetailData(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
)
