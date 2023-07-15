package com.gabniel.elibrary.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gabniel.elibrary.database.entities.EbookEntity

@Dao
interface EbookDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveEbook(data: ArrayList<EbookEntity>)

    @Query("SELECT * FROM ebook WHERE genre = :genre")
    fun getRomanceBook(genre: String): LiveData<List<EbookEntity>>

    @Query("SELECT * FROM ebook WHERE genre = :genre")
    fun getPhilosophyBook(genre: String): LiveData<List<EbookEntity>>

}