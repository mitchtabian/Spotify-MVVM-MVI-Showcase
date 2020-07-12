package com.sean.spotifyapp.screens.playlist_detail

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.sean.myapplication.screens.BaseClasses.BaseFragment
import com.sean.myapplication.screens.BaseClasses.BaseFragmentView
import com.sean.myapplication.screens.BaseClasses.ViewState
import com.sean.spotifyapp.R
import com.sean.spotifyapp.screens.menu.MenuViewModel
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class PlaylistDetailFragment(viewModelFactory: ViewModelProvider.Factory) : BaseFragment(viewModelFactory) {

    private val viewModel: PlaylistDetailViewModel by viewModels {viewModelFactory}

    companion object {
         const val BUNDLE_KEY_PLAYLIST_ID = "playlistId"
         const val BUNDLE_KEY_PLAYLIST_NAME = "playlistName"
    }

    override fun getToolbarMenu(): Int = R.menu.menu_playlist_detail

    override fun initBaseView(): BaseFragmentView<ViewState>{
        val playlistId = arguments?.getString(BUNDLE_KEY_PLAYLIST_ID)
        val playlistName = arguments?.getString(BUNDLE_KEY_PLAYLIST_NAME)
        return context?.let { PlaylistDetailFragmentView(playlistId!!, playlistName!!, viewModel, it) } as BaseFragmentView<ViewState>
    }

}