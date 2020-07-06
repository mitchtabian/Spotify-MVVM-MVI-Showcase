package com.sean.spotifyapp.models.responses

import com.sean.spotifyapp.models.Image
import com.sean.spotifyapp.models.Owner
import com.sean.spotifyapp.models.Tracks

data class PlaylistDetailResponse(
    val collaborative: Boolean?,
    val description: String?,
    val href: String?,
    val id: String?,
    val images: List<Image>?,
    val name: String?,
    val owner: Owner?,
    val primaryColor: String?,
    val public: Boolean?,
    val snapshotId: String?,
    val tracks: Tracks?,
    val type: String?,
    val uri: String
)