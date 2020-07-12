package com.sean.spotifyapp.screens.search

import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.sean.myapplication.screens.BaseClasses.*
import com.sean.spotifyapp.R
import com.sean.spotifyapp.screens.playlists.PlaylistsViewModel
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class SearchFragment(
    viewModelFactory: ViewModelProvider.Factory
) : BaseFragment(viewModelFactory) {

    private val viewModel: SearchViewModel by viewModels {viewModelFactory}

    override fun getToolbarMenu(): Int = R.menu.menu_search

    override fun initBaseView(): BaseFragmentView<ViewState> =
        context?.let { SearchFragmentView(viewModel, it) } as BaseFragmentView<ViewState>

}