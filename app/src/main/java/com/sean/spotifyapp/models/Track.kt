package com.sean.spotifyapp.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "track")
data class Track(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "pk")
    var id: String,

    @ColumnInfo(name = "href")
    val href: String?,

    @ColumnInfo(name = "album")
    val album: Album?,

    @ColumnInfo(name = "artists")
    val artists: List<Artist>?,

    @ColumnInfo(name = "available_markets")
    val availableMarkets: List<String>?,

    @ColumnInfo(name = "disc_number")
    val discNumber: Int?,

    @ColumnInfo(name = "duration_ms")
    val durationMs: Long?,

    @ColumnInfo(name = "episode")
    val episode: Boolean?,

    @ColumnInfo(name = "explicit")
    val explicit: Boolean?,

    @ColumnInfo(name = "is_local")
    val isLocal: Boolean?,

    @ColumnInfo(name = "name")
    val name: String?,

    @ColumnInfo(name = "popularity")
    val popularity: Int?,

    @ColumnInfo(name = "preview_url")
    val previewUrl: String?,

    @ColumnInfo(name = "track")
    val track: Boolean?,

    @ColumnInfo(name = "track_number")
    val track_number: String?,

    @ColumnInfo(name = "type")
    val type: String?,

    @ColumnInfo(name = "uri")
    val uri: String?,

    @ColumnInfo(name = "associated_playlists")
    var associatedPlaylists: MutableList<String?>?

) : Parcelable