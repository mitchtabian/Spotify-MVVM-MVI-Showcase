package com.sean.spotifyapp.network

import com.sean.spotifyapp.models.responses.CategoriesResponse
import com.sean.spotifyapp.models.responses.PlaylistDetailResponse
import com.sean.spotifyapp.models.responses.PlaylistsResponse
import com.sean.spotifyapp.models.responses.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {


    @GET("search")
    suspend fun search(
        @Header("Authorization") authorization: String,
        @Query("q") query: String,
        @Query("type") type: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): SearchResponse


    @GET("browse/categories")
    suspend fun getCategories(
        @Header("Authorization") authorization: String
    ): CategoriesResponse


    @GET("me/playlists")
    suspend fun getPlaylists(
        @Header("Authorization") authorization: String, @Query("limit") limit: Int, @Query("offset") offset: Int
    ): PlaylistsResponse


    @GET("playlists/{playlist_id}")
    suspend fun getPlaylistDetail(
        @Header("Authorization") authorization: String, @Path("playlist_id") id: String
    ): PlaylistDetailResponse


}