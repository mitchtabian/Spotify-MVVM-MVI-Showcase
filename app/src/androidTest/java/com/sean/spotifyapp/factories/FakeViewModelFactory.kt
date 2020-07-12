package com.sean.spotifyapp.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sean.spotifyapp.repository.FakeMainRepository
import com.sean.spotifyapp.screens.categories.CategoriesFragment
import com.sean.spotifyapp.screens.categories.CategoriesViewModel
import com.sean.spotifyapp.screens.menu.MenuViewModel
import com.sean.spotifyapp.screens.playlist_detail.PlaylistDetailViewModel
import com.sean.spotifyapp.screens.playlists.PlaylistsViewModel
import com.sean.spotifyapp.screens.search.SearchViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
class FakeMainViewModelFactory
@Inject
constructor(
    private val mainRepository: FakeMainRepository
): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CategoriesViewModel::class.java)) {
            return CategoriesViewModel(mainRepository) as T
        }

        if (modelClass.isAssignableFrom(MenuViewModel::class.java)) {
            return MenuViewModel(mainRepository) as T
        }

        if (modelClass.isAssignableFrom(PlaylistsViewModel::class.java)) {
            return PlaylistsViewModel(mainRepository) as T
        }

        if (modelClass.isAssignableFrom(PlaylistDetailViewModel::class.java)) {
            return PlaylistDetailViewModel(mainRepository) as T
        }

        if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
            return SearchViewModel(mainRepository) as T
        }


        throw IllegalArgumentException("Unknown ViewModel class")
    }
}