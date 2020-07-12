package com.sean.spotifyapp.repository.apihandlers

import com.sean.myapplication.screens.BaseClasses.StateEvent
import com.sean.spotifyapp.R
import com.sean.spotifyapp.getString
import com.sean.spotifyapp.models.PlaylistItem
import com.sean.spotifyapp.models.responses.PlaylistsResponse
import com.sean.spotifyapp.network.ApiService
import com.sean.spotifyapp.persistence.AuthDao
import com.sean.spotifyapp.persistence.MainDao
import com.sean.spotifyapp.repository.apihandlers.base.CacheRetrievingApiHandler
import com.sean.spotifyapp.repository.successToastMessageModel
import com.sean.spotifyapp.screens.base.DataState
import com.sean.spotifyapp.screens.playlists.PlaylistsViewState
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class GetPlaylistsHandler(
    private val stateEvent: StateEvent,
    private val apiService: ApiService,
    private val mainDao: MainDao,
    private val tokenString: suspend () -> String
) : CacheRetrievingApiHandler<PlaylistsResponse, List<PlaylistItem>, PlaylistsViewState>(stateEvent,
    apiCall = { apiService.getPlaylists(tokenString.invoke(), 50, 0) },
    cacheCall = { mainDao.getAllPlaylists() }) {


    override fun createSuccessViewStateFromCache(
        data: List<PlaylistItem>,
        stateEvent: StateEvent?
    ): DataState<PlaylistsViewState> =
        DataState(
            null,
            PlaylistsViewState(data),
            stateEvent,
            null)

    override suspend fun saveToCache(data: PlaylistsResponse) {
        val playlists = data.items
        for (playlist in playlists) {
            removeTracksIfPlaylistUpdated(playlist.id, playlist.snapshotId)
            mainDao.insertPlaylist(playlist)
        }
    }

    private suspend fun removeTracksIfPlaylistUpdated(playlistId: String, webSnapshotId: String?){
        val cachedPlaylist = mainDao.getPlaylistById(playlistId)
        if (cachedPlaylist != null && cachedPlaylist.snapshotId != webSnapshotId) {
            val cachedTracks = mainDao.getTracksByPlaylistId(playlistId)
            for (cachedTrack in cachedTracks) {
                val associatedPlaylists = cachedTrack.associatedPlaylists
                associatedPlaylists?.remove(playlistId)
                cachedTrack.associatedPlaylists = associatedPlaylists
                mainDao.insertTrack(cachedTrack)
            }
        }
    }
}