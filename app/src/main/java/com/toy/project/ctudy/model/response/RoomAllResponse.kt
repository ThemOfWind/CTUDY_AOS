package com.toy.project.ctudy.model.response

import com.google.gson.annotations.SerializedName

data class RoomAllResponse(
    @SerializedName("response")
    val responseList: ArrayList<RoomAllResponseList>,
) : BaseResponse()

data class RoomAllResponseList(
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("member_count")
    val member_count: String?,
    @SerializedName("master_name")
    val master_name: String?,
    @SerializedName("imageUrl")
    val imagePath: String? = "http://mixedcode.com/Upload/Board/meetreviewtitle.jpg",
)