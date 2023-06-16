package com.tenan.android.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "search_history")
data class SearchHistoryEntity(

    @PrimaryKey
    @ColumnInfo(name = "query")
    val query: String,

    @ColumnInfo(name = "created_at")
    val createdAt: Long
)
