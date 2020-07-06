package com.sean.spotifyapp.screens.playlist_detail.widgets

import android.util.Log
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sean.spotifyapp.models.Track

class PlaylistDetailAdapter(private val data: List<Track>) : RecyclerView.Adapter<PlaylistDetailAdapter.PlaylistDetailViewHolder>() {

    class PlaylistDetailViewHolder(val view: PlaylistDetailRowView) : RecyclerView.ViewHolder(view)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistDetailViewHolder =
        PlaylistDetailViewHolder(PlaylistDetailRowView(parent.context))

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: PlaylistDetailViewHolder, position: Int) {
        holder.view.title.text = data[position].name
    }
}