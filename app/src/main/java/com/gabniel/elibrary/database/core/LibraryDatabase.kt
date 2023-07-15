package com.gabniel.elibrary.database.core

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gabniel.elibrary.database.dao.AccountDao
import com.gabniel.elibrary.database.dao.BookMarkDao
import com.gabniel.elibrary.database.dao.EbookDao
import com.gabniel.elibrary.database.entities.AccountEntity
import com.gabniel.elibrary.database.entities.BookmarkEntity
import com.gabniel.elibrary.database.entities.EbookEntity

@Database(
    entities = [
        AccountEntity::class,
        EbookEntity::class,
        BookmarkEntity::class
    ], version = 1, exportSchema = false
)
abstract class LibraryDatabase : RoomDatabase() {
    abstract fun accountDao(): AccountDao
    abstract fun ebookDao(): EbookDao
    abstract fun bookmarkDao(): BookMarkDao

}