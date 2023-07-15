package com.gabniel.elibrary.module

import android.content.Context
import com.gabniel.elibrary.utils.AppManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideAppManager(@ApplicationContext context: Context) = AppManager(context)

}