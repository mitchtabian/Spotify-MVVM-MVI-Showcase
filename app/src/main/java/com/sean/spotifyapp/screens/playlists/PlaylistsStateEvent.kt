package com.sean.spotifyapp.screens.playlists

import com.sean.myapplication.screens.BaseClasses.StateEvent

sealed class PlaylistsStateEvent: StateEvent() {

    class GetPlaylistsStateEvent: PlaylistsStateEvent()

    class GoToPlaylistStateEvent(val playlistId: String, val playlistName: String): PlaylistsStateEvent()

}