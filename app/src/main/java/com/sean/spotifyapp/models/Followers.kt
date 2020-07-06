package com.sean.spotifyapp.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Followers(val href: String?, val total: Int?): Parcelable