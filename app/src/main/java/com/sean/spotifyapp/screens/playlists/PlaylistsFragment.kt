package com.sean.spotifyapp.screens.playlists

import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.sean.myapplication.screens.BaseClasses.BaseFragment
import com.sean.myapplication.screens.BaseClasses.BaseFragmentView
import com.sean.myapplication.screens.BaseClasses.ViewState
import com.sean.spotifyapp.R
import com.sean.spotifyapp.screens.menu.MenuFragmentView
import com.sean.spotifyapp.screens.playlist_detail.PlaylistDetailViewModel
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class PlaylistsFragment(viewModelFactory: ViewModelProvider.Factory) : BaseFragment(viewModelFactory) {

    private val viewModel: PlaylistsViewModel by viewModels {viewModelFactory}

    override fun getToolbarMenu(): Int = R.menu.menu_playlists

    override fun initBaseView(): BaseFragmentView<ViewState> =
        context?.let { PlaylistsFragmentView(viewModel, it) } as BaseFragmentView<ViewState>
}