package com.sean.spotifyapp.models

data class ArtistsResponse(
    val href: String,
    val total: Int,
    val next: String,
    val offset: Int,
    val previous: String,
    val limit: Int,
    val items: List<Artist>
)
