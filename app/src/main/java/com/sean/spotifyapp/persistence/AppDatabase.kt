package com.sean.spotifyapp.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sean.spotifyapp.models.Artist
import com.sean.spotifyapp.models.CategoryItem
import com.sean.spotifyapp.models.PlaylistItem
import com.sean.spotifyapp.models.Track
import com.sean.spotifyapp.models.AuthToken
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
@Database(
    entities = [CategoryItem::class, Artist::class, AuthToken::class, PlaylistItem::class, Track::class],
    version = 5
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getMainDao(): MainDao

    abstract fun getAuthDao(): AuthDao

    companion object {
        const val DATABASE_NAME: String = "app_db"
    }
}




