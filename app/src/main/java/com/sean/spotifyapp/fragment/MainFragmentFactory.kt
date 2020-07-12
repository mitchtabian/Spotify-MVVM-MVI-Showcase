package com.sean.spotifyapp.fragment

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.lifecycle.ViewModelProvider
import com.sean.myapplication.di.MainScope
import com.sean.spotifyapp.screens.categories.CategoriesFragment
import com.sean.spotifyapp.screens.menu.MenuFragment
import com.sean.spotifyapp.screens.playlist_detail.PlaylistDetailFragment
import com.sean.spotifyapp.screens.playlists.PlaylistsFragment
import com.sean.spotifyapp.screens.search.SearchFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import javax.inject.Inject
import javax.inject.Named

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
@MainScope
class MainFragmentFactory
@Inject
constructor(
    private val viewModelFactory: ViewModelProvider.Factory
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        when (className) {
            SearchFragment::class.java.name -> return SearchFragment(viewModelFactory)
            CategoriesFragment::class.java.name -> return CategoriesFragment(viewModelFactory)
            MenuFragment::class.java.name -> return MenuFragment(viewModelFactory)
            PlaylistsFragment::class.java.name -> return PlaylistsFragment(viewModelFactory)
            PlaylistDetailFragment::class.java.name -> return PlaylistDetailFragment(viewModelFactory)
        }
        return super.instantiate(classLoader, className)
    }
}