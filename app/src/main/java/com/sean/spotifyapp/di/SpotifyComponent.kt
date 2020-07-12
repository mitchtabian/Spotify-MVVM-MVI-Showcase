package com.sean.spotifyapp.di

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import com.sean.spotifyapp.di.auth.AuthComponent
import com.sean.spotifyapp.di.main.MainComponent
import com.sean.spotifyapp.di.module.AppModule
import com.sean.spotifyapp.di.module.SubComponentModule
import com.sean.spotifyapp.fragment.MainNavHostFragment
import dagger.BindsInstance
import dagger.Component
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
@Singleton
@Component(
    modules = [
        SubComponentModule::class,
        AppModule::class
    ]
)
interface SpotifyComponent {

    fun inject(activity: AppCompatActivity)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder
        fun build(): SpotifyComponent
    }

    fun authComponent(): AuthComponent.Builder

    fun mainComponent(): MainComponent.Factory


}