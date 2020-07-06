package com.sean.spotifyapp.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "auth_token")
@Parcelize
data class AuthToken(

    @PrimaryKey
    @ColumnInfo(name = "account_pk")
    var account_pk: Long ,

    @ColumnInfo(name = "token")
    @SerializedName("token")
    @Expose
    var token: String,

    @ColumnInfo(name = "time")
    @SerializedName("time")
    @Expose
    var time: Long
) : Parcelable



