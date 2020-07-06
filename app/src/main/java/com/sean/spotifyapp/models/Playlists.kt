package com.sean.spotifyapp.models

data class Playlists(
    val href: String,
    val items: List<PlaylistItem>,
    val limit: Int,
    val next: String,
    val offset: Int,
    val previous: String,
    val total: Int
)