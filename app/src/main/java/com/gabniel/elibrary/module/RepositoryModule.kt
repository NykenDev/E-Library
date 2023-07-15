package com.gabniel.elibrary.module

import com.gabniel.elibrary.database.dao.AccountDao
import com.gabniel.elibrary.database.dao.BookMarkDao
import com.gabniel.elibrary.database.dao.EbookDao
import com.gabniel.elibrary.repository.AccountRepository
import com.gabniel.elibrary.repository.EBookRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideAccountRepository(
        accountDao: AccountDao,
    ) = AccountRepository(accountDao)

    @Provides
    @Singleton
    fun provideBookRepository(ebookDao: EbookDao, bookMarkDao: BookMarkDao) = EBookRepository(ebookDao, bookMarkDao)

}