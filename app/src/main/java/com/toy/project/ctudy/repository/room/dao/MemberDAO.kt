package com.toy.project.ctudy.repository.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.toy.project.ctudy.repository.room.entity.MemberEntity

@Dao
interface MemberDAO {
    @Query("SELECT * FROM member")
    fun getMemberList(): List<MemberEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMember(member: MemberEntity)

    @Query("SELECT * FROM member WHERE username = :name")
    fun getSearchMemberList(name: String): List<MemberEntity>
}