package com.tenan.android.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tenan.android.data.source.local.dao.SearchHistoryDao
import com.tenan.android.data.source.local.dao.UserAccountDao
import com.tenan.android.data.source.local.entity.SearchHistoryEntity
import com.tenan.android.data.source.local.entity.UserAccountEntity

@Database(
    entities = [
        SearchHistoryEntity::class,
        UserAccountEntity::class
    ],
    version = TenanDatabase.DATABASE_VERSION,
    exportSchema = false
)
abstract class TenanDatabase : RoomDatabase() {
    abstract fun searchHistoryDao(): SearchHistoryDao
    abstract fun userAccountDao(): UserAccountDao
    companion object {
        const val DATABASE_VERSION = 1
    }
}