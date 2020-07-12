package com.sean.spotifyapp.screens.auth

import com.sean.myapplication.screens.BaseClasses.BaseViewModel
import com.sean.myapplication.screens.BaseClasses.StateEvent
import com.sean.spotifyapp.repository.AuthRepository
import com.sean.spotifyapp.screens.base.DataState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
@InternalCoroutinesApi
class AuthViewModel
@Inject
constructor(private val authRepository: AuthRepository) : BaseViewModel<AuthViewState>() {

    override fun getStateEventFlow(event: StateEvent): Flow<DataState<AuthViewState>> = when (event) {
        is AuthStateEvent.AuthenticateSpotifyStateEvent -> authRepository.authenticateSpotify(event)

        else -> {
            flow {}

        }
    }

    override fun initViewState(): AuthViewState = AuthViewState()

}