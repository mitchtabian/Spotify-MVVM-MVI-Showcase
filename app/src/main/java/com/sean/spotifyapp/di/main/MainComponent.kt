package com.sean.spotifyapp.di.main

import com.sean.myapplication.di.MainScope
import com.sean.spotifyapp.activity.MainActivity
import com.sean.spotifyapp.di.main.module.MainDaoModule
import com.sean.spotifyapp.di.main.module.MainFragmentFactoryModule
import com.sean.spotifyapp.di.main.module.MainRepositoryModule
import com.sean.spotifyapp.di.main.module.MainViewModelModule
import com.sean.spotifyapp.fragment.MainNavHostFragment
import dagger.Subcomponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
@ExperimentalCoroutinesApi
@MainScope
@Subcomponent(modules = [
    MainViewModelModule::class,
    MainFragmentFactoryModule::class,
    MainDaoModule::class
] )
interface MainComponent {

    fun inject(navHostFragment: MainNavHostFragment)

    @Subcomponent.Factory
    interface Factory{
        fun create(): MainComponent
    }

}