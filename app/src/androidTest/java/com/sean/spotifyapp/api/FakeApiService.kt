package com.sean.spotifyapp.api

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sean.spotifyapp.models.responses.CategoriesResponse
import com.sean.spotifyapp.models.responses.PlaylistDetailResponse
import com.sean.spotifyapp.models.responses.PlaylistsResponse
import com.sean.spotifyapp.models.responses.SearchResponse
import com.sean.spotifyapp.network.ApiService
import com.sean.spotifyapp.util.Constants.CATEGORIES_FILENAME
import com.sean.spotifyapp.util.Constants.PLAYLISTS_FILENAME
import com.sean.spotifyapp.util.Constants.PLAYLIST_DETAIL_FILENAME
import com.sean.spotifyapp.util.Constants.SEARCH_FILENAME
import com.sean.spotifyapp.util.JsonUtil
import kotlinx.coroutines.delay
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FakeApiService
@Inject
constructor(
    private val jsonUtil: JsonUtil
) : ApiService {

    var networkDelay: Long = 0L

    override suspend fun search(
        authorization: String,
        query: String,
        type: String,
        limit: Int,
        offset: Int
    ): SearchResponse {
        val rawJson = jsonUtil.readJSONFromAsset(SEARCH_FILENAME)
        val searchResponse = Gson().fromJson<SearchResponse>(
            rawJson,
            object : TypeToken<CategoriesResponse>() {}.type
        )
        delay(networkDelay)
        return searchResponse
    }

    override suspend fun getCategories(authorization: String): CategoriesResponse {
        val rawJson = jsonUtil.readJSONFromAsset(CATEGORIES_FILENAME)
        val categoriesResponse = Gson().fromJson<CategoriesResponse>(
            rawJson,
            object : TypeToken<CategoriesResponse>() {}.type
        )
        delay(networkDelay)
        return categoriesResponse
    }

    override suspend fun getPlaylists(authorization: String, limit: Int, offset: Int): PlaylistsResponse {
        val rawJson = jsonUtil.readJSONFromAsset(PLAYLISTS_FILENAME)
        val playlistsResponse = Gson().fromJson<PlaylistsResponse>(
            rawJson,
            object : TypeToken<PlaylistsResponse>() {}.type
        )
        delay(networkDelay)
        return playlistsResponse
    }

    override suspend fun getPlaylistDetail(authorization: String, id: String): PlaylistDetailResponse {
        val rawJson = jsonUtil.readJSONFromAsset(PLAYLIST_DETAIL_FILENAME)
        val playlistDetailResponse = Gson().fromJson<PlaylistDetailResponse>(
            rawJson,
            object : TypeToken<PlaylistsResponse>() {}.type
        )
        delay(networkDelay)
        return playlistDetailResponse
    }

}