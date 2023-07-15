package com.gabniel.elibrary.repository

import com.gabniel.elibrary.database.dao.AccountDao
import com.gabniel.elibrary.database.entities.AccountEntity
import javax.inject.Inject

class AccountRepository @Inject constructor(
    private val accountDao: AccountDao,
) {

    suspend fun saveAccount(data: AccountEntity) {
        accountDao.saveAccount(data)
    }

    fun getAccount(email: String, password: String) {
        accountDao.getAccount(email, password)
    }

}