package com.sean.spotifyapp.repository

import com.sean.myapplication.screens.BaseClasses.StateEvent
import com.sean.spotifyapp.R
import com.sean.spotifyapp.SpotifyApplication
import com.sean.spotifyapp.getString
import com.sean.spotifyapp.persistence.AuthDao
import com.sean.spotifyapp.persistence.CacheResult
import com.sean.spotifyapp.screens.auth.AuthReturnAction
import com.sean.spotifyapp.screens.auth.AuthStateEvent
import com.sean.spotifyapp.screens.auth.AuthViewState
import com.sean.spotifyapp.screens.base.DataState
import com.sean.spotifyapp.models.AuthToken
import com.spotify.sdk.android.auth.AuthorizationResponse
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import java.util.*
import javax.inject.Inject

@InternalCoroutinesApi
class AuthRepository
@Inject
constructor(private val authDao: AuthDao) {

    companion object {
        const val TOKEN_PRIMARY_KEY = 0L
    }

    fun authenticateSpotify(stateEvent: AuthStateEvent.AuthenticateSpotifyStateEvent): Flow<DataState<AuthViewState>> {
        return flow {
            if (checkIfNeedsToken()) {
                val channel: Channel<AuthorizationResponse> = Channel()
                stateEvent.authActivity.showSpotifyLogin(channel)
                //suspends until received
                val authResponse = channel.receive()
                resolveAuthResponse(authResponse, stateEvent, this)
            } else {
                emit(createSuccessDataState(stateEvent))
            }
        }
    }

    private suspend fun resolveAuthResponse(
        authResponse: AuthorizationResponse,
        stateEvent: StateEvent,
        flow: FlowCollector<DataState<AuthViewState>>
    ) {
        when (authResponse.type) {
            AuthorizationResponse.Type.TOKEN -> {
                val currentTime = Calendar.getInstance().timeInMillis
                val expirationTime = currentTime + (authResponse.expiresIn * 1000)
                val authToken =
                    AuthToken(
                        TOKEN_PRIMARY_KEY,
                        authResponse.accessToken,
                        expirationTime
                    )
                val result = authDao.insertToken(authToken)
                if (result == 0L && !authResponse.accessToken.isBlank()) {
                    flow.emit(createSuccessDataState(stateEvent))
                } else {
                    flow.emit(
                        createErrorToastDataState(
                            authResponse.error,
                            stateEvent,
                            AuthReturnAction.ReShowSpotifyDialogAction()
                        )
                    )
                }
            }

            AuthorizationResponse.Type.ERROR -> {
                flow.emit(
                    createErrorDialogDataState(
                        getString(R.string.dialog_message_error_auth_no_internet),
                        stateEvent, AuthReturnAction.ReShowSpotifyDialogAction()
                    )
                )
            }

            else -> flow.emit(
                createErrorToastDataState(
                    SpotifyApplication.sApplication.getString(R.string.dialog_message_error_auth_dismiss),
                    stateEvent,
                    AuthReturnAction.ReShowSpotifyDialogAction()
                )
            )
        }

    }


    private fun createSuccessDataState(stateEvent: StateEvent): DataState<AuthViewState> {
        val authViewState = AuthViewState()
        val action = AuthReturnAction.GoToMainActivityAction()
        return DataState(null, authViewState, stateEvent, action)
    }

    private suspend fun checkIfNeedsToken(): Boolean {
        val cacheResult = safeCacheCall { authDao.getToken() }
        when (cacheResult) {
            is CacheResult.Error -> return true
            is CacheResult.Success -> {
                if (cacheResult.data == null) {
                    return true
                } else {
                    if (cacheResult.data.token.isBlank()) {
                        return true
                    }
                }
            }
        }
        val currentTime = Calendar.getInstance().timeInMillis
        return currentTime > cacheResult.data.time
    }

}