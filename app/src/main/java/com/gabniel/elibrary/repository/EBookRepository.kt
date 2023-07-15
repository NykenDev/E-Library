package com.gabniel.elibrary.repository

import androidx.lifecycle.LiveData
import com.gabniel.elibrary.database.dao.BookMarkDao
import com.gabniel.elibrary.database.dao.EbookDao
import com.gabniel.elibrary.database.entities.BookmarkEntity
import com.gabniel.elibrary.database.entities.EbookEntity
import javax.inject.Inject

class EBookRepository @Inject constructor(
    private val ebookDao: EbookDao,
    private val bookmarkDao: BookMarkDao,
) {

    suspend fun saveBookmark(data: BookmarkEntity) {
        bookmarkDao.saveBookmark(data)
    }

    fun getRomanceBook(): LiveData<List<EbookEntity>> {
        return ebookDao.getRomanceBook("romance")
    }

    fun getPhilosophyBook(): LiveData<List<EbookEntity>> {
        return ebookDao.getPhilosophyBook("philosophy")
    }

    fun getBookmark(): LiveData<List<BookmarkEntity>> {
        return bookmarkDao.getBookmark()
    }

    suspend fun deleteItemBookmark(name: String) {
        bookmarkDao.deleteItemBookmark(name)
    }

}