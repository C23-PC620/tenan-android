package com.tenan.android.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_account")
data class UserAccountEntity(

    @PrimaryKey
    @ColumnInfo(name = "refresh_token")
    val refreshToken: String,

    @ColumnInfo(name = "access_token")
    val accessToken: String
)
