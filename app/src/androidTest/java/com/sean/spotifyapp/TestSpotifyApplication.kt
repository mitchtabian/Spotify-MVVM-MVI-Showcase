package com.sean.spotifyapp

import com.sean.spotifyapp.di.DaggerTestAppComponent
import com.sean.spotifyapp.di.auth.AuthComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
class TestBaseApplication : SpotifyApplication(){


    override fun initAppComponent(){
        appComponent = DaggerTestAppComponent.builder().application(this).build()
    }


}
