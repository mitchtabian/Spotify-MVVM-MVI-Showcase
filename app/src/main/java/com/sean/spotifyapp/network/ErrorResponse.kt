package com.sean.spotifyapp.network

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize

data class ErrorResponse(val error: SpotifyError) : Parcelable