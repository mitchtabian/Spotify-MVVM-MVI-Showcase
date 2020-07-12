package com.sean.spotifyapp.di.main.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sean.spotifyapp.viewmodel.MainViewModelFactory
import com.sean.spotifyapp.di.main.MainViewModelKey
import com.sean.spotifyapp.screens.categories.CategoriesViewModel
import com.sean.spotifyapp.screens.menu.MenuViewModel
import com.sean.spotifyapp.screens.playlist_detail.PlaylistDetailViewModel
import com.sean.spotifyapp.screens.playlists.PlaylistsViewModel
import com.sean.spotifyapp.screens.search.SearchViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
@Module
abstract class MainViewModelModule{


    @Binds
    abstract fun bindViewModelFactory(factory: MainViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @MainViewModelKey(SearchViewModel::class)
    abstract fun bindSearchViewModel(searchViewModel: SearchViewModel): ViewModel

    @Binds
    @IntoMap
    @MainViewModelKey(CategoriesViewModel::class)
    abstract fun bindCategoriesViewModel(categoriesViewModel: CategoriesViewModel): ViewModel

    @Binds
    @IntoMap
    @MainViewModelKey(MenuViewModel::class)
    abstract fun bindMenuViewModel(menuViewModel: MenuViewModel): ViewModel

    @Binds
    @IntoMap
    @MainViewModelKey(PlaylistsViewModel::class)
    abstract fun bindPlaylistsViewModel(playlistsViewModel: PlaylistsViewModel): ViewModel

    @Binds
    @IntoMap
    @MainViewModelKey(PlaylistDetailViewModel::class)
    abstract fun bindPlaylistViewModel(playlistDetailViewModel: PlaylistDetailViewModel): ViewModel


}