package com.sean.spotifyapp.di.module

import androidx.fragment.app.FragmentFactory
import com.sean.spotifyapp.factories.FakeMainFragmentFactory
import com.sean.spotifyapp.factories.FakeMainViewModelFactory
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import javax.inject.Named
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
@Module
object TestFragmentModule {

    @JvmStatic
    @Singleton
    @Provides
    fun provideMainFragmentFactory(
        viewModelFactory: FakeMainViewModelFactory
    ): FragmentFactory {
        return FakeMainFragmentFactory(viewModelFactory)
    }
}








