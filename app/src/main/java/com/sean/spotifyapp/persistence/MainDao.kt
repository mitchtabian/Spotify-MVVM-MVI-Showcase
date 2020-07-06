package com.sean.spotifyapp.persistence

import androidx.room.*
import com.sean.spotifyapp.models.Artist
import com.sean.spotifyapp.models.CategoryItem
import com.sean.spotifyapp.models.PlaylistItem
import com.sean.spotifyapp.models.Track

@Dao
interface MainDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(category: CategoryItem): Long

    @Query("""
        SELECT * FROM category_item 
        """)
    suspend fun getAllCategories(): List<CategoryItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArtist(artist: Artist): Long

    @Query("DELETE FROM category_item")
    suspend fun deleteAllCategories()

    @Query("""
        SELECT * FROM artist_item 
        WHERE name LIKE '%' || :query || '%' 
        """)
    suspend fun searchArtists(
        query: String
    ): List<Artist>

    @Query("""SELECT  * FROM playlist_item""")
    suspend fun getAllPlaylists(): List<PlaylistItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlaylist(playlistItem: PlaylistItem): Long

    @Query("DELETE FROM playlist_item")
    suspend fun deleteAllPlaylists()

    @Query("SELECT * FROM playlist_item WHERE pk = :id")
    fun getPlaylistById(id: String?): PlaylistItem

    @Query("""SELECT  * FROM track""")
    suspend fun getAllTracks(): List<Track>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTrack(track: Track?): Long

    @Query("DELETE FROM track")
    suspend fun deleteAllTracks()

    @Query("SELECT * FROM track WHERE pk = :id")
    fun getTrack(id: String?): Track

    @Query("SELECT * FROM track WHERE associated_playlists LIKE '%' || :id ||  '%' ")
    fun getTracksByPlaylistId(id: String?): List<Track>


}