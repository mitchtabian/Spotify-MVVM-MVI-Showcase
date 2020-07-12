package com.sean.spotifyapp.di.module
import androidx.lifecycle.ViewModelProvider
import com.sean.spotifyapp.factories.FakeMainViewModelFactory
import com.sean.spotifyapp.repository.FakeMainRepository
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
@Module
object TestViewModelModule {

    @JvmStatic
    @Singleton
    @Provides
    fun provideViewModelFactory(
        mainRepository: FakeMainRepository
    ): ViewModelProvider.Factory {
        return FakeMainViewModelFactory(
            mainRepository
        )
    }
}













