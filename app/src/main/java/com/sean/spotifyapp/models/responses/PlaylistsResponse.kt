package com.sean.spotifyapp.models.responses

import com.sean.spotifyapp.models.PlaylistItem
import com.sean.spotifyapp.models.Playlists

data class PlaylistsResponse(
    val href: String,
    val items: List<PlaylistItem>,
    val limit: Int,
    val next: String,
    val offset: Int, val previous: String,
    val total: Int
) : BaseSpotifyResponse()