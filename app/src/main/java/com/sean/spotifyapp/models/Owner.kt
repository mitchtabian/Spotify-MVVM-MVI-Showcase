package com.sean.spotifyapp.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Owner(
    val displayName: String, val href: String, val id: String, val type: String, val uri: String
) :Parcelable