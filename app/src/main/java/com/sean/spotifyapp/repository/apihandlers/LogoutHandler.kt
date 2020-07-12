package com.sean.spotifyapp.repository.apihandlers

import androidx.appcompat.view.menu.MenuView
import com.sean.myapplication.screens.BaseClasses.ViewState
import com.sean.spotifyapp.R
import com.sean.spotifyapp.getString
import com.sean.spotifyapp.persistence.AuthDao
import com.sean.spotifyapp.persistence.CacheResult
import com.sean.spotifyapp.repository.errorToastMessageModel
import com.sean.spotifyapp.repository.safeCacheCall
import com.sean.spotifyapp.repository.successToastMessageModel
import com.sean.spotifyapp.screens.base.DataState
import com.sean.spotifyapp.screens.menu.MenuReturnAction
import com.sean.spotifyapp.screens.menu.MenuStateEvent
import com.sean.spotifyapp.screens.menu.MenuViewState
import com.spotify.sdk.android.auth.AuthorizationClient
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.flow
@InternalCoroutinesApi
class LogoutHandler(val stateEvent: MenuStateEvent.LogoutEvent, private val authDao: AuthDao) {

    val result = flow {
        AuthorizationClient.clearCookies(stateEvent.context)
        emit(resolveLocalLogout())
    }

    private suspend fun resolveLocalLogout(): DataState<MenuViewState> {
        return when (safeCacheCall { authDao.logOut() }) {
            is CacheResult.Error ->
                DataState<MenuViewState>(
                    errorToastMessageModel(getString(R.string.dialog_message_error_logout)),
                    null,
                    stateEvent,
                    null)

            is CacheResult.Success ->
                DataState<MenuViewState>(
                    successToastMessageModel(getString(R.string.dialog_message_success_logout)),
                    null,
                    stateEvent,
                    MenuReturnAction.LogoutAction())
        }
    }
}