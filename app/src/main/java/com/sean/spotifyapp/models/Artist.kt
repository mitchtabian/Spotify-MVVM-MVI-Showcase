package com.sean.spotifyapp.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Parcelize
@Entity(tableName = "artist_item")
data class Artist(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "pk")
    val id: String,

    @ColumnInfo(name = "genres")
    val genres: List<String>,

    @ColumnInfo(name = "href")
    val href: String,

    @ColumnInfo(name = "followers")
    val followers: Followers,

    @ColumnInfo(name = "images")
    val images: List<Image>,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "popularity")
    val popularity: Int,

    @ColumnInfo(name = "type")
    val type: String,

    @ColumnInfo(name = "uri")
    val uri: String
) : Parcelable