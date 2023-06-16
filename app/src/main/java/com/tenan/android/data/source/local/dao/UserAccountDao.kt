package com.tenan.android.data.source.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import com.tenan.android.data.source.local.entity.UserAccountEntity

@Dao
interface UserAccountDao : BaseDao<UserAccountEntity> {

    @Query("SELECT * FROM user_account")
    suspend fun getUserToken(): UserAccountEntity?

    @Update
    suspend fun updateToken(userAccountEntity: UserAccountEntity)

    @Query("DELETE FROM user_account")
    suspend fun clear()

}