package com.sean.spotifyapp.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "category_item")
data class CategoryItem(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "pk")
    val id: String,

    @ColumnInfo(name = "href")
    val href: String,

    @ColumnInfo(name = "icons")
    val icons: List<CategoryItem>,

    @ColumnInfo(name = "name")
    val name: String
) : Parcelable
