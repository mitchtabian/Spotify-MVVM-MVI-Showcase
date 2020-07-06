package com.sean.spotifyapp.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Album(
    val albumType: String,
    val artists: List<Artist>,
    val availableMarkets: List<String>,
    val href: String,
    val id: String,
    val images: List<Image>,
    val name: String,
    val releaseDate: String,
    val releaseDatePrecision: String,
    val totalTracks: Int,
    val type: String,
    val uri: String
): Parcelable