package com.sean.spotifyapp.screens.playlist_detail

import android.content.Context
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.sean.myapplication.screens.BaseClasses.BaseFragmentView
import com.sean.myapplication.screens.BaseClasses.BaseViewModel
import com.sean.myapplication.screens.BaseClasses.ViewState
import com.sean.spotifyapp.R
import com.sean.spotifyapp.screens.base.ReturnAction
import com.sean.spotifyapp.screens.playlist_detail.widgets.PlaylistDetailAdapter
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class PlaylistDetailFragmentView(
    private val playlistId: String,
    private val playlistName: String,
    viewModel: PlaylistDetailViewModel,
    context: Context
) : BaseFragmentView<PlaylistDetailViewState>(viewModel, context) {

    private lateinit var recyclerView: RecyclerView

    override fun onViewStateChanged(detailViewState: PlaylistDetailViewState) {
        recyclerView.adapter = PlaylistDetailAdapter(detailViewState.tracks)
    }

    override fun getLayoutRes(): Int = R.layout.fragment_playlist_detail

    override fun initViews(menu: Menu?) {
        recyclerView = findViewById(R.id.RecyclerView_playlist_detail)
    }

    override fun initialAction() {
        setStateEvent(PlaylistDetailStateEvent.GetPlaylistDetailStateEvent(playlistId))
    }

    override fun initTitle(): String = playlistName
}