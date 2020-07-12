package com.sean.spotifyapp.screens.playlists

import android.content.Context
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sean.myapplication.screens.BaseClasses.BaseFragmentView
import com.sean.myapplication.screens.BaseClasses.BaseViewModel
import com.sean.myapplication.screens.BaseClasses.ViewState
import com.sean.spotifyapp.R
import com.sean.spotifyapp.getString
import com.sean.spotifyapp.screens.base.ReturnAction
import com.sean.spotifyapp.screens.playlist_detail.PlaylistDetailFragment.Companion.BUNDLE_KEY_PLAYLIST_ID
import com.sean.spotifyapp.screens.playlist_detail.PlaylistDetailFragment.Companion.BUNDLE_KEY_PLAYLIST_NAME
import com.sean.spotifyapp.screens.playlists.widgets.PlaylistsAdapter
import kotlinx.coroutines.InternalCoroutinesApi
@InternalCoroutinesApi
class PlaylistsFragmentView(viewModel: PlaylistsViewModel, context: Context) :
    BaseFragmentView<PlaylistsViewState>(viewModel, context), PlaylistsAdapter.Callback {

    private lateinit var recyclerView: RecyclerView


    override fun onViewStateChanged(viewState: PlaylistsViewState) {
        recyclerView.adapter = PlaylistsAdapter(viewState.playlistItems, this)
    }

    override fun getLayoutRes(): Int = R.layout.fragment_playlists

    override fun initViews(menu: Menu?) {
        recyclerView = findViewById(R.id.RecyclerView_playlists)
        recyclerView.layoutManager = LinearLayoutManager(context)
    }

    override fun resolveAction(action: ReturnAction) {
        when (action) {
            is PlaylistsReturnAction.GoToPlaylistFragmentAction -> {
                val bundle = bundleOf(
                    BUNDLE_KEY_PLAYLIST_ID to action.playlistId,
                    BUNDLE_KEY_PLAYLIST_NAME to action.playlistName
                )
                findNavController().navigate(R.id.action_playlistsFragment_to_playlistFragment, bundle)
            }
        }
    }

    override fun initialAction() {
        setStateEvent(PlaylistsStateEvent.GetPlaylistsStateEvent())
    }

    override fun onPlaylistSelected(id: String, name: String) {
        setStateEvent(PlaylistsStateEvent.GoToPlaylistStateEvent(id, name))
    }

    override fun initTitle(): String = getString(R.string.toolbar_title_playlists)
}