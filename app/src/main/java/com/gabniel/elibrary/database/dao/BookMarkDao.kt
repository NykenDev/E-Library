package com.gabniel.elibrary.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gabniel.elibrary.database.entities.BookmarkEntity

@Dao
interface BookMarkDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveBookmark(data: BookmarkEntity)

    @Query("SELECT * FROM bookmark")
    fun getBookmark(): LiveData<List<BookmarkEntity>>

    @Query("DELETE FROM bookmark WHERE name = :name")
   suspend fun deleteItemBookmark(name: String)

}