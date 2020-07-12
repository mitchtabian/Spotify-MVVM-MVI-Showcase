package com.sean.spotifyapp

import android.app.Activity
import android.app.Application
import com.sean.spotifyapp.di.DaggerSpotifyComponent
import com.sean.spotifyapp.di.SpotifyComponent
import com.sean.spotifyapp.di.auth.AuthComponent
import com.sean.spotifyapp.di.main.MainComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
open class SpotifyApplication : Application() {


    lateinit var appComponent: SpotifyComponent

    private var mainComponent: MainComponent? = null

    private var authComponent: AuthComponent? = null

    companion object {
        lateinit var sApplication: SpotifyApplication
    }

    override fun onCreate() {
        sApplication = this
        super.onCreate()
        initAppComponent()
    }

    fun releaseMainComponent() {
        mainComponent = null
    }

    fun mainComponent(): MainComponent {
        if(mainComponent == null){
            mainComponent = appComponent.mainComponent().create()
        }
        return mainComponent as MainComponent
    }

    fun releaseAuthComponent() {
        authComponent = null
    }

    fun authComponent(): AuthComponent {
        if(authComponent == null){
            authComponent = appComponent.authComponent().build()
        }
        return authComponent as AuthComponent
    }

    protected open fun initAppComponent(){
        appComponent = DaggerSpotifyComponent.builder().application(this).build()
    }

}