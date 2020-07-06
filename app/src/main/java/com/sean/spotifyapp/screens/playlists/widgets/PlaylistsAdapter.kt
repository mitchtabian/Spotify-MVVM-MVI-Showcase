package com.sean.spotifyapp.screens.playlists.widgets

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sean.spotifyapp.models.PlaylistItem

class PlaylistsAdapter(private val data: List<PlaylistItem>, private val callback: Callback) :
    RecyclerView.Adapter<PlaylistsAdapter.PlaylistViewHolder>() {

    interface Callback {
        fun onPlaylistSelected(id: String, name: String)
    }

    class PlaylistViewHolder(val view: PlaylistRowView) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistViewHolder =
        PlaylistViewHolder(PlaylistRowView(parent.context))

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: PlaylistViewHolder, position: Int) {
        val playlist = data[position]
        holder.view.setPlaylist(playlist)
        holder.view.setOnClickListener {
            callback.onPlaylistSelected(playlist.id, playlist.name)
        }
    }

}