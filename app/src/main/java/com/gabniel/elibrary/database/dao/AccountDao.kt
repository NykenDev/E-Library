package com.gabniel.elibrary.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gabniel.elibrary.database.entities.AccountEntity

@Dao
interface AccountDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAccount(data: AccountEntity)

    @Query("SELECT * FROM account WHERE username = (:username) AND password = :password")
    fun getAccount(username: String, password: String): Boolean
}