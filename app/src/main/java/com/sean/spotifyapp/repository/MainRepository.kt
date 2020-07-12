package com.sean.spotifyapp.repository

import com.sean.myapplication.screens.BaseClasses.StateEvent
import com.sean.spotifyapp.network.*
import com.sean.spotifyapp.persistence.AuthDao
import com.sean.spotifyapp.persistence.MainDao
import com.sean.spotifyapp.screens.base.DataState
import com.sean.spotifyapp.screens.categories.CategoriesViewState
import com.sean.spotifyapp.screens.menu.MenuStateEvent
import com.sean.spotifyapp.screens.menu.MenuViewState
import com.sean.spotifyapp.screens.playlists.PlaylistsViewState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import com.sean.spotifyapp.repository.apihandlers.*
import com.sean.spotifyapp.screens.playlist_detail.PlaylistDetailStateEvent.*
import com.sean.spotifyapp.screens.playlist_detail.PlaylistDetailViewState
import com.sean.spotifyapp.screens.playlists.PlaylistsStateEvent.*
import com.sean.spotifyapp.screens.search.SearchStateEvent.*
import com.sean.spotifyapp.screens.search.SearchViewState
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
open class MainRepository
@Inject
constructor(private val apiService: ApiService, private val mainDao: MainDao, private val authDao: AuthDao) {

    open fun logout(stateEvent: MenuStateEvent.LogoutEvent): Flow<DataState<MenuViewState>> =
        LogoutHandler(stateEvent, authDao).result

    open fun searchArtists(stateArtistsEvent: SearchArtistsEvent): Flow<DataState<SearchViewState>> =
        GetArtistsHandler(stateArtistsEvent, apiService, mainDao, { getTokenString() }).result

    open fun getPlaylists(stateEvent: GetPlaylistsStateEvent): Flow<DataState<PlaylistsViewState>> =
        GetPlaylistsHandler(stateEvent, apiService, mainDao, { getTokenString() }).result

    open fun getPlaylistDetail(stateEvent: GetPlaylistDetailStateEvent): Flow<DataState<PlaylistDetailViewState>> =
        GetPlaylistDetailHandler(stateEvent, apiService, mainDao, { getTokenString() }).result

    open fun getCategories(stateEvent: StateEvent): Flow<DataState<CategoriesViewState>> =
        GetCategoriesHandler(stateEvent, apiService, mainDao, { getTokenString() }).result

    private suspend fun getTokenString(): String = "Bearer ${authDao.getToken()?.token}"
}