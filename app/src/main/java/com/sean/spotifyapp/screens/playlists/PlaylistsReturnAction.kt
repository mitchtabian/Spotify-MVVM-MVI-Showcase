package com.sean.spotifyapp.screens.playlists

import com.sean.spotifyapp.screens.base.ReturnAction

sealed class PlaylistsReturnAction: ReturnAction() {

    class GoToPlaylistFragmentAction(val playlistId: String, val playlistName: String): ReturnAction()

}