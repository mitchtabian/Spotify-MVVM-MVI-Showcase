package com.sean.spotifyapp.di.auth

import com.sean.myapplication.di.modules.AuthFragmentFactoryModule
import com.sean.spotifyapp.activity.AuthActivity
import com.sean.spotifyapp.di.auth.module.AuthViewModelModule
import com.sean.spotifyapp.fragment.AuthNavHostFragment
import com.sean.spotifyapp.fragment.MainNavHostFragment
import dagger.Subcomponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
@AuthScope
@Subcomponent(
    modules = [
        AuthViewModelModule::class,
        AuthFragmentFactoryModule::class
    ]
)
interface AuthComponent {

    fun inject(navHostFragment: AuthNavHostFragment)

    fun inject(authActivity: AuthActivity)

    @Subcomponent.Builder
    interface Builder {
        fun build(): AuthComponent
    }

}