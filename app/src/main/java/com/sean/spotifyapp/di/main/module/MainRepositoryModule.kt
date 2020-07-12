package com.sean.spotifyapp.di.main.module

import com.sean.myapplication.di.MainScope
import com.sean.spotifyapp.network.ApiService
import com.sean.spotifyapp.persistence.AuthDao
import com.sean.spotifyapp.repository.MainRepository
import com.sean.spotifyapp.persistence.MainDao
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
@Module
class MainRepositoryModule {

    @MainScope
    @Provides
    fun provideMainRepository(apiService: ApiService, mainDao: MainDao, authDao: AuthDao): MainRepository {
        return MainRepository(apiService, mainDao, authDao)
    }

}