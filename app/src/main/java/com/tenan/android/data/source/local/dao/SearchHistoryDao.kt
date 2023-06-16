package com.tenan.android.data.source.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.tenan.android.data.source.local.entity.SearchHistoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SearchHistoryDao : BaseDao<SearchHistoryEntity> {

    @Query("SELECT * FROM search_history ORDER BY search_history.created_at")
    fun getSearchData(): Flow<List<SearchHistoryEntity>>

    @Query("DELETE FROM search_history")
    suspend fun clear()

}