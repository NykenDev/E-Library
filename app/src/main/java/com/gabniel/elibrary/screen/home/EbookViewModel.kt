package com.gabniel.elibrary.screen.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gabniel.elibrary.database.entities.BookmarkEntity
import com.gabniel.elibrary.database.entities.EbookEntity
import com.gabniel.elibrary.repository.EBookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EbookViewModel @Inject constructor(private val repository: EBookRepository) : ViewModel() {

    fun saveBookmark(data: BookmarkEntity) {
        viewModelScope.launch {
            repository.saveBookmark(data)
        }
    }

    val getRomanceBook: LiveData<List<EbookEntity>> get() = repository.getRomanceBook()

    val getPhilosophyBook: LiveData<List<EbookEntity>> get() = repository.getPhilosophyBook()

    val getBookmark: LiveData<List<BookmarkEntity>> get() = repository.getBookmark()

    fun deleteItemBookmark(name: String) {
        viewModelScope.launch {
            repository.deleteItemBookmark(name)
        }
    }

}