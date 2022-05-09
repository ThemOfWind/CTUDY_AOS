package com.toy.project.ctudy.model.response

import com.google.gson.annotations.SerializedName

data class MemberListResponse(
    @SerializedName("response")
    val response: MemberResponse,
) : BaseResponse()

data class MemberResponse(
    @SerializedName("count")
    val count: String,
    @SerializedName("next")
    val next: String,
    @SerializedName("items")
    var memberList: ArrayList<MemberList>,
)

data class MemberList(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("image")
    val image: String,
)