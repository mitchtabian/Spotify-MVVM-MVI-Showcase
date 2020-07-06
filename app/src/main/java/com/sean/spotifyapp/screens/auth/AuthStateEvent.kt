package com.sean.spotifyapp.screens.auth

import com.sean.myapplication.screens.BaseClasses.StateEvent
import com.sean.spotifyapp.activity.AuthActivity

sealed class AuthStateEvent: StateEvent() {

    class AuthenticateSpotifyStateEvent(val authActivity: AuthActivity): AuthStateEvent()



}