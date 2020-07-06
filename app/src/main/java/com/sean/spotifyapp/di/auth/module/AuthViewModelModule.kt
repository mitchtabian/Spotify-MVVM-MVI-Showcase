package com.sean.spotifyapp.di.auth.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sean.spotifyapp.di.auth.AuthViewModelKey
import com.sean.spotifyapp.viewmodel.MainViewModelFactory
import com.sean.spotifyapp.screens.auth.AuthViewModel
import com.sean.spotifyapp.viewmodel.AuthViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class AuthViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: AuthViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @AuthViewModelKey(AuthViewModel::class)
    abstract fun bindAuthViewModel(authViewModel: AuthViewModel): ViewModel


}