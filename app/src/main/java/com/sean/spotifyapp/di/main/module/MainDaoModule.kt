package com.sean.spotifyapp.di.main.module

import com.sean.myapplication.di.MainScope
import com.sean.spotifyapp.persistence.AppDatabase
import com.sean.spotifyapp.persistence.MainDao
import dagger.Module
import dagger.Provides

@Module
class MainDaoModule {

    @MainScope
    @Provides
    fun provideMainDao(db: AppDatabase): MainDao = db.getMainDao()

}