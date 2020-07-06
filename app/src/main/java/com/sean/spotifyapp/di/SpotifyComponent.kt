package com.sean.spotifyapp.di

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import com.sean.spotifyapp.di.auth.AuthComponent
import com.sean.spotifyapp.di.main.MainComponent
import com.sean.spotifyapp.di.module.AppModule
import com.sean.spotifyapp.di.module.SubComponentModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    SubComponentModule::class,
    AppModule::class])
interface SpotifyComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder
        fun build(): SpotifyComponent
    }

    fun inject(activity: AppCompatActivity)

    fun authComponent(): AuthComponent.Builder

    fun mainComponent(): MainComponent.Factory





}