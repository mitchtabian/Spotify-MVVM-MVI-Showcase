package com.sean.spotifyapp.di

import android.app.Application
import com.sean.spotifyapp.api.FakeApiService
import com.sean.spotifyapp.di.module.*
import com.sean.spotifyapp.fragment.MainNavHostFragment
import com.sean.spotifyapp.repository.FakeMainRepository
import com.sean.spotifyapp.ui.MenuTest
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
        TestFragmentModule::class,
        TestViewModelModule::class,
        TestAppModule::class
    ]
)
interface TestAppComponent : SpotifyComponent {


    fun inject(menuTest: MenuTest)


    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(app: Application): Builder

        fun build(): TestAppComponent
    }


}

