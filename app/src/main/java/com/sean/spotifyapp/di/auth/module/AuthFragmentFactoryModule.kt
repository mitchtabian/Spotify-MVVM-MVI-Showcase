package com.sean.myapplication.di.modules

import androidx.fragment.app.FragmentFactory
import androidx.lifecycle.ViewModelProvider
import com.sean.spotifyapp.di.auth.AuthScope
import com.sean.spotifyapp.fragment.AuthFragmentFactory
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
@Module
class AuthFragmentFactoryModule {

    @AuthScope
    @Provides
    fun provideAppFragmentFactory(
        viewModelFactory: ViewModelProvider.Factory
    ): FragmentFactory = AuthFragmentFactory(viewModelFactory)

}