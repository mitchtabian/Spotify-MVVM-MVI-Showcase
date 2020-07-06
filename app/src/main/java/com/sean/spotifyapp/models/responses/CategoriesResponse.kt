package com.sean.spotifyapp.models.responses

import com.sean.spotifyapp.models.Category
import com.sean.spotifyapp.models.CategoryItem
import com.sean.spotifyapp.models.Playlists

data class CategoriesResponse (val categories: Category): BaseSpotifyResponse()