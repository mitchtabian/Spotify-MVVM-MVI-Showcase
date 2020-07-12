package com.sean.spotifyapp.screens.playlists

import com.sean.myapplication.screens.BaseClasses.BaseViewModel
import com.sean.myapplication.screens.BaseClasses.StateEvent
import com.sean.spotifyapp.R
import com.sean.spotifyapp.getString
import com.sean.spotifyapp.network.UIComponentType
import com.sean.spotifyapp.repository.MainRepository
import com.sean.spotifyapp.repository.buildError
import com.sean.spotifyapp.screens.base.DataState
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
@InternalCoroutinesApi
class PlaylistsViewModel
@Inject
constructor(val mainRepository: MainRepository) : BaseViewModel<PlaylistsViewState>() {
    @InternalCoroutinesApi
    override fun getStateEventFlow(event: StateEvent): Flow<DataState<PlaylistsViewState>> = when (event) {
        is PlaylistsStateEvent.GetPlaylistsStateEvent -> {
            mainRepository.getPlaylists(event)
        }

        is PlaylistsStateEvent.GoToPlaylistStateEvent -> {
            flow {
                if (!event.playlistId.isEmpty()) {
                    emit(DataState<PlaylistsViewState>(
                            null, null, event,
                            PlaylistsReturnAction.GoToPlaylistFragmentAction(event.playlistId, event.playlistName)))
                } else {
                    emit(buildError<PlaylistsViewState>(
                            getString(R.string.dialog_message_error_no_playlist_id),
                            UIComponentType.Dialog(),
                            event))
                }
            }
        }

        else -> {
            flow {}
        }
    }

    override fun initViewState(): PlaylistsViewState = PlaylistsViewState(listOf())
}