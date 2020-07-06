package com.sean.spotifyapp.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "track_item")
data class TrackItem(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "pk")
    val id: String,

    @ColumnInfo(name = "href")
    val href: String?,

    @ColumnInfo(name = "added_at")
    val addedAt: String?,

    @ColumnInfo(name = "is_local")
    val isLocal: String?,

    @ColumnInfo(name = "primary_color")
    val primaryColor: String?,

    @ColumnInfo(name = "track")
    val track: Track?

) : Parcelable