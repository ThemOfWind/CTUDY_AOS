package com.toy.project.ctudy.repository.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "member")
class MemberEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val username: String,
)