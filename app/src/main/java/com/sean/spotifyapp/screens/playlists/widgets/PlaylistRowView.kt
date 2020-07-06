package com.sean.spotifyapp.screens.playlists.widgets

import android.content.Context
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sean.spotifyapp.R
import com.sean.spotifyapp.models.PlaylistItem

class PlaylistRowView(context: Context): FrameLayout(context) {

    init {
        inflate(context, R.layout.rowview_playlist, this)
    }


    fun setPlaylist(playlist: PlaylistItem){
        findViewById<TextView>(R.id.TextView_playlist_name).text = playlist.name
    }

}