package com.sean.spotifyapp.di.main.module

import androidx.fragment.app.FragmentFactory
import androidx.lifecycle.ViewModelProvider
import com.sean.myapplication.di.MainScope
import com.sean.spotifyapp.fragment.MainFragmentFactory
import com.sean.spotifyapp.viewmodel.MainViewModelFactory
import com.sean.spotifyapp.viewmodel.MainViewModelFactory_Factory
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import javax.inject.Named

@InternalCoroutinesApi
@ExperimentalCoroutinesApi
@Module
object MainFragmentFactoryModule {

    @JvmStatic
    @MainScope
    @Provides
    fun provideAppFragmentFactory(
        viewModelFactory: MainViewModelFactory
    ): FragmentFactory = MainFragmentFactory(viewModelFactory)

}