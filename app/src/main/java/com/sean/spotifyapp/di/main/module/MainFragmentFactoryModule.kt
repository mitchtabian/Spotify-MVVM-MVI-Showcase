package com.sean.spotifyapp.di.main.module

import androidx.fragment.app.FragmentFactory
import androidx.lifecycle.ViewModelProvider
import com.sean.myapplication.di.MainScope
import com.sean.spotifyapp.fragment.MainFragmentFactory
import dagger.Module
import dagger.Provides

@Module
class MainFragmentFactoryModule {

    @MainScope
    @Provides
    fun provideAppFragmentFactory(
        viewModelFactory: ViewModelProvider.Factory
    ): FragmentFactory = MainFragmentFactory(viewModelFactory)

}