package com.sean.spotifyapp.repository

import com.sean.myapplication.screens.BaseClasses.StateEvent
import com.sean.spotifyapp.api.FakeApiService
import com.sean.spotifyapp.models.Track
import com.sean.spotifyapp.models.responses.CategoriesResponse
import com.sean.spotifyapp.models.responses.PlaylistDetailResponse
import com.sean.spotifyapp.network.ApiService
import com.sean.spotifyapp.persistence.AuthDao
import com.sean.spotifyapp.persistence.MainDao
import com.sean.spotifyapp.repository.apihandlers.base.BaseApiHandler
import com.sean.spotifyapp.screens.base.DataState
import com.sean.spotifyapp.screens.categories.CategoriesViewState
import com.sean.spotifyapp.screens.menu.MenuStateEvent
import com.sean.spotifyapp.screens.menu.MenuViewState
import com.sean.spotifyapp.screens.playlist_detail.PlaylistDetailStateEvent
import com.sean.spotifyapp.screens.playlist_detail.PlaylistDetailViewState
import com.sean.spotifyapp.screens.playlists.PlaylistsStateEvent
import com.sean.spotifyapp.screens.playlists.PlaylistsViewState
import com.sean.spotifyapp.screens.search.SearchStateEvent
import com.sean.spotifyapp.screens.search.SearchViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@InternalCoroutinesApi
@Singleton
class FakeMainRepository
@Inject
constructor(private val apiService: FakeApiService, private val mainDao: MainDao) :
    MainRepository(apiService, mainDao, null!!) {

    @Throws(UninitializedPropertyAccessException::class)
    override fun logout(stateEvent: MenuStateEvent.LogoutEvent): Flow<DataState<MenuViewState>> {
        return super.logout(stateEvent)
    }

    @Throws(UninitializedPropertyAccessException::class)
    override fun searchArtists(stateArtistsEvent: SearchStateEvent.SearchArtistsEvent): Flow<DataState<SearchViewState>> {
        return super.searchArtists(stateArtistsEvent)
    }

    @Throws(UninitializedPropertyAccessException::class)
    override fun getPlaylists(stateEvent: PlaylistsStateEvent.GetPlaylistsStateEvent): Flow<DataState<PlaylistsViewState>> {
        return super.getPlaylists(stateEvent)
    }

    @Throws(UninitializedPropertyAccessException::class)
    override fun getPlaylistDetail(stateEvent: PlaylistDetailStateEvent.GetPlaylistDetailStateEvent): Flow<DataState<PlaylistDetailViewState>> {
        return object :
            BaseApiHandler<PlaylistDetailViewState, PlaylistDetailResponse>(
                stateEvent,
                { apiService.getPlaylistDetail("", "") }) {

            override suspend fun handleApiCallSuccess(data: PlaylistDetailResponse): DataState<PlaylistDetailViewState> {
                val tracks = ArrayList<Track?>()
                for (trackItem in data.tracks?.items!!) {
                    val track = trackItem?.track
                    tracks.add(track)
                }
                return DataState(
                    null,
                    PlaylistDetailViewState(tracks as List<Track>),
                    stateEvent,
                    null)
            }
        }.result
    }

    @Throws(UninitializedPropertyAccessException::class)
    override fun getCategories(stateEvent: StateEvent): Flow<DataState<CategoriesViewState>> {
        return object :
            BaseApiHandler<CategoriesViewState, CategoriesResponse>(stateEvent, { apiService.getCategories("") }) {
            override suspend fun handleApiCallSuccess(data: CategoriesResponse): DataState<CategoriesViewState> {
                return DataState(
                    null,
                    CategoriesViewState(data.categories.items),
                    stateEvent,
                    null
                )
            }
        }.result
    }

}