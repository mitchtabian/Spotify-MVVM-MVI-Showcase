package com.sean.spotifyapp.screens.playlist_detail

import com.sean.myapplication.screens.BaseClasses.BaseViewModel
import com.sean.myapplication.screens.BaseClasses.StateEvent
import com.sean.spotifyapp.repository.MainRepository
import com.sean.spotifyapp.screens.base.DataState
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
@InternalCoroutinesApi
class PlaylistDetailViewModel
@Inject
constructor(val mainRepository: MainRepository) : BaseViewModel<PlaylistDetailViewState>() {

    override fun getStateEventFlow(event: StateEvent): Flow<DataState<PlaylistDetailViewState>> {
        return when (event) {
            is PlaylistDetailStateEvent.GetPlaylistDetailStateEvent -> mainRepository.getPlaylistDetail(event)
            else -> flow {}
        }
    }

    override fun initViewState(): PlaylistDetailViewState = PlaylistDetailViewState(listOf())
}