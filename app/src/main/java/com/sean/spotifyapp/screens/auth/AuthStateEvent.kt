package com.sean.spotifyapp.screens.auth

import com.sean.myapplication.screens.BaseClasses.StateEvent
import com.sean.spotifyapp.activity.AuthActivity
import kotlinx.coroutines.InternalCoroutinesApi

sealed class AuthStateEvent: StateEvent() {

    @InternalCoroutinesApi
    class AuthenticateSpotifyStateEvent(val authActivity: AuthActivity): AuthStateEvent()



}