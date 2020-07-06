package com.sean.spotifyapp.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Tracks(
    val href: String,
    val items: List<TrackItem>,
    val limit: Int?,
    val next: String?,
    val offset: Int?,
    val previous: String?,
    val total: Int?
) : Parcelable