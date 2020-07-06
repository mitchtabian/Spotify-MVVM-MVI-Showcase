package com.sean.spotifyapp.screens.playlist_detail.widgets

import android.content.Context
import android.widget.FrameLayout
import android.widget.TextView
import com.sean.spotifyapp.R

class PlaylistDetailRowView(context: Context) : FrameLayout(context){
    val title: TextView

    init {
        inflate(context, R.layout.rowview_track, this)
        title = findViewById(R.id.TextView_track_title)
    }
}