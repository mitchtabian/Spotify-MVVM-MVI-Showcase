package com.sean.spotifyapp.repository.apihandlers

import com.sean.myapplication.screens.BaseClasses.StateEvent
import com.sean.spotifyapp.R
import com.sean.spotifyapp.getString
import com.sean.spotifyapp.models.PlaylistItem
import com.sean.spotifyapp.models.Track
import com.sean.spotifyapp.models.responses.PlaylistDetailResponse
import com.sean.spotifyapp.models.responses.PlaylistsResponse
import com.sean.spotifyapp.network.ApiService
import com.sean.spotifyapp.persistence.MainDao
import com.sean.spotifyapp.repository.apihandlers.base.CacheRetrievingApiHandler
import com.sean.spotifyapp.repository.successToastMessageModel
import com.sean.spotifyapp.screens.base.DataState
import com.sean.spotifyapp.screens.playlist_detail.PlaylistDetailStateEvent
import com.sean.spotifyapp.screens.playlist_detail.PlaylistDetailStateEvent.*
import com.sean.spotifyapp.screens.playlist_detail.PlaylistDetailViewState
import com.sean.spotifyapp.screens.playlists.PlaylistsViewState
import kotlinx.coroutines.InternalCoroutinesApi
import java.util.*
@InternalCoroutinesApi
class GetPlaylistDetailHandler(
    private val stateEvent: GetPlaylistDetailStateEvent,
    private val apiService: ApiService,
    private val mainDao: MainDao,
    private val tokenString: suspend () -> String
) : CacheRetrievingApiHandler<PlaylistDetailResponse, List<Track>, PlaylistDetailViewState>(stateEvent,
    apiCall = { apiService.getPlaylistDetail(tokenString.invoke(), stateEvent.playlistId) },
    cacheCall = { mainDao.getTracksByPlaylistId(stateEvent.playlistId) }) {

    override fun createSuccessViewStateFromCache(
        data: List<Track>,
        stateEvent: StateEvent?
    ): DataState<PlaylistDetailViewState> = DataState(
        null,
        PlaylistDetailViewState(data),
        stateEvent,
        null
    )

    override suspend fun saveToCache(data: PlaylistDetailResponse) {
        val tracks = data.tracks?.items
        tracks?.let {
            for (trackItem in tracks) {
                val track = trackItem?.track
                val cachedTrack = mainDao.getTrack(track?.id)
                if (cachedTrack == null) {
                    insertNewTrack(data.id, track)
                } else {
                    addAssociatedPlaylistsFromCacheAndInsert(cachedTrack, data.id, track)
                }
            }
        }
    }

    private suspend fun insertNewTrack(playlistId: String?, track: Track?) {
        val associatedPlaylists = mutableListOf<String?>()
        if (track?.id.isNullOrBlank()) {
            //If a local track, generate an ID
            track?.id = UUID.nameUUIDFromBytes(track?.name?.toByteArray()).toString()
        }
        if (!associatedPlaylists.contains(playlistId)) {
            associatedPlaylists.add(playlistId)
        }
        track?.associatedPlaylists = associatedPlaylists
        mainDao.insertTrack(track)
    }

    private suspend fun addAssociatedPlaylistsFromCacheAndInsert(
        cachedTrack: Track?,
        playlistId: String?,
        networkTrack: Track?
    ) {
        val associatedPlaylists = cachedTrack?.associatedPlaylists
        associatedPlaylists?.add(playlistId)
        networkTrack?.associatedPlaylists = associatedPlaylists
        mainDao.insertTrack(networkTrack)
    }

}