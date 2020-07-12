package com.sean.spotifyapp.repository.apihandlers

import android.util.Log
import com.google.gson.Gson
import com.sean.myapplication.screens.BaseClasses.StateEvent
import com.sean.spotifyapp.models.Artist
import com.sean.spotifyapp.models.responses.SearchResponse
import com.sean.spotifyapp.network.ApiService
import com.sean.spotifyapp.persistence.MainDao
import com.sean.spotifyapp.repository.apihandlers.base.CacheRetrievingApiHandler
import com.sean.spotifyapp.screens.base.DataState
import com.sean.spotifyapp.screens.search.SearchStateEvent.SearchArtistsEvent
import com.sean.spotifyapp.screens.search.SearchViewState
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class GetArtistsHandler(
    private val stateArtistsEvent: SearchArtistsEvent,
    private val apiService: ApiService,
    private val mainDao: MainDao,
    private val tokenString: suspend () -> String
) : CacheRetrievingApiHandler<SearchResponse, List<Artist>, SearchViewState>(stateArtistsEvent,
    apiCall = {
        apiService.search(
            tokenString.invoke(),
            stateArtistsEvent.query,
            "artist",
            50,
            stateArtistsEvent.offset
        )
    },
    cacheCall = { mainDao.searchArtists(stateArtistsEvent.query) }) {

    override fun createSuccessViewStateFromCache(
        data: List<Artist>,
        stateEvent: StateEvent?
    ): DataState<SearchViewState> {
        val newData = ArrayList<Artist>()
        newData.addAll(data)
        return DataState(
            null,
            SearchViewState(newData, stateArtistsEvent.query, stateArtistsEvent.offset + 50),
            stateEvent,
            null
        )
    }

    override suspend fun saveToCache(data: SearchResponse) {
        val artistsResponse = data.artists
        if (artistsResponse != null) {
            val artistItems = artistsResponse.items
            for (item in artistItems) {
                mainDao.insertArtist(item)
            }
        }
    }
}