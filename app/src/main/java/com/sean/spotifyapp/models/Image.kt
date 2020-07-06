package com.sean.spotifyapp.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Image(val height: Int, val width: Int, val url: String) : Parcelable