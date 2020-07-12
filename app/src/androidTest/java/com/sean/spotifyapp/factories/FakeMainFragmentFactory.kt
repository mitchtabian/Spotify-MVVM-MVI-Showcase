package com.sean.spotifyapp.factories

import androidx.fragment.app.FragmentFactory
import androidx.lifecycle.ViewModelProvider
import com.sean.spotifyapp.screens.categories.CategoriesFragment
import com.sean.spotifyapp.screens.menu.MenuFragment
import com.sean.spotifyapp.screens.playlist_detail.PlaylistDetailFragment
import com.sean.spotifyapp.screens.playlists.PlaylistsFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import javax.inject.Inject
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
@Singleton
class FakeMainFragmentFactory
@Inject
constructor(
    private val viewModelFactory: ViewModelProvider.Factory
): FragmentFactory(){

    // used for setting a mock<UICommunicationListener>
//    lateinit var uiCommunicationListener: UICommunicationListener

    override fun instantiate(classLoader: ClassLoader, className: String) =

        when(className){

            MenuFragment::class.java.name -> {
                val fragment = MenuFragment(viewModelFactory)
//                if(::uiCommunicationListener.isInitialized){
//                    fragment.setUICommunicationListener(uiCommunicationListener)
//                }
                fragment
            }

            PlaylistsFragment::class.java.name -> {
                val fragment = PlaylistsFragment(viewModelFactory)
                fragment
            }

            CategoriesFragment::class.java.name -> {
                val fragment = CategoriesFragment(viewModelFactory)
                fragment
            }

            PlaylistDetailFragment::class.java.name -> {
                val fragment = CategoriesFragment(viewModelFactory)
                fragment
            }

            else -> {
                super.instantiate(classLoader, className)
            }
        }
}
