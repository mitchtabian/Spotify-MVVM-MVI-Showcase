package com.sean.spotifyapp.screens.playlist_detail

import com.sean.myapplication.screens.BaseClasses.StateEvent

sealed class PlaylistDetailStateEvent: StateEvent() {

    class GetPlaylistDetailStateEvent(val playlistId: String): PlaylistDetailStateEvent()


}