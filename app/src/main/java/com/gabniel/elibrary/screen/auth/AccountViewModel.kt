package com.gabniel.elibrary.screen.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gabniel.elibrary.database.entities.AccountEntity
import com.gabniel.elibrary.repository.AccountRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(private val repository: AccountRepository) :
    ViewModel() {

    fun saveAccount(username: String, email: String, password: String) {
        viewModelScope.launch {
            repository.saveAccount(AccountEntity(null, username, email, password))
        }
    }

    fun getAccount(email: String, password: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.getAccount(email, password)
            }
        }
    }


}