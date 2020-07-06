package com.sean.spotifyapp.screens.auth

import com.sean.spotifyapp.screens.base.ReturnAction

sealed class AuthReturnAction: ReturnAction() {

    class GoToMainActivityAction: AuthReturnAction()

    class ReShowSpotifyDialogAction: AuthReturnAction()

}