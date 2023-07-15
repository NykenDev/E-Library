package com.gabniel.elibrary.module

import android.app.Application
import androidx.room.Room
import com.gabniel.elibrary.database.core.LibraryDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideLibraryDatabase(application: Application) = Room.databaseBuilder(
        application, LibraryDatabase::class.java, "LibraryDB"
    ).build()

    @Provides
    @Singleton
    fun provideAccountDao(db: LibraryDatabase) = db.accountDao()

    @Provides
    @Singleton
    fun provideEbookDao(db: LibraryDatabase) = db.ebookDao()

    @Provides
    @Singleton
    fun provideBookmarkDao(db: LibraryDatabase) = db.bookmarkDao()

}
