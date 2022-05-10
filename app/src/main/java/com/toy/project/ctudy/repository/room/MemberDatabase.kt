package com.toy.project.ctudy.repository.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.toy.project.ctudy.repository.room.dao.MemberDAO
import com.toy.project.ctudy.repository.room.entity.MemberEntity

@Database(entities = [MemberEntity::class], version = 1)
abstract class MemberDatabase : RoomDatabase() {
    abstract fun getMemberDao(): MemberDAO

    companion object {
        private var _instance: MemberDatabase? = null
        fun getInstance(context: Context): MemberDatabase? {
            if (_instance == null) {
                synchronized(MemberDatabase::class) {
                    _instance = Room.databaseBuilder(context.applicationContext,
                        MemberDatabase::class.java, "member.db").build()
                }
            }
            return _instance
        }
    }
}