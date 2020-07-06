package com.sean.spotifyapp.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "playlist_item")
data class PlaylistItem(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "pk")
    val id: String,

    @ColumnInfo(name = "href")
    val href: String?,

    @ColumnInfo(name = "description")
    val description : String?,

    @ColumnInfo(name = "collaborative")
    val collaborative : String?,

    @ColumnInfo(name = "genres")
    val genres: List<String>?,

    @ColumnInfo(name = "images")
    val images: List<Image>?,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "owner")
    val owner: Owner?,

    @ColumnInfo(name = "primary_color")
    val primaryColor: String?,

    @ColumnInfo(name = "public")
    @SerializedName("public")
    @Expose
    val isPublic: Boolean?,

    @ColumnInfo(name = "snapshot_id")
    val snapshotId: String?,

    @ColumnInfo(name = "tracks")
    val tracks: Tracks?,

    @ColumnInfo(name = "type")
    val type: String?,

    @ColumnInfo(name = "uri")
    val uri: String?

) : Parcelable