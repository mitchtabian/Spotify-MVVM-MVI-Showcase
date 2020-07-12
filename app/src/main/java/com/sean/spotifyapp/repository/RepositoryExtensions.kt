package com.sean.spotifyapp.repository

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import com.sean.myapplication.screens.BaseClasses.StateEvent
import com.sean.myapplication.screens.BaseClasses.ViewState
import com.sean.spotifyapp.R
import com.sean.spotifyapp.SpotifyApplication
import com.sean.spotifyapp.getString
import com.sean.spotifyapp.network.MessageModel
import com.sean.spotifyapp.network.MessageType
import com.sean.spotifyapp.network.UIComponentType
import com.sean.spotifyapp.persistence.CacheResult
import com.sean.spotifyapp.screens.auth.AuthReturnAction
import com.sean.spotifyapp.screens.auth.AuthViewState
import com.sean.spotifyapp.screens.base.DataState
import com.sean.spotifyapp.screens.base.ReturnAction
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.withContext


@InternalCoroutinesApi
suspend fun <T> safeCacheCall(cacheCall: suspend () -> T): CacheResult<T> {
    return withContext(Dispatchers.IO) {
        try {
            CacheResult.Success(cacheCall.invoke())
        } catch (throwable: Throwable) {
            when (throwable) {
                is TimeoutCancellationException -> CacheResult.Error(getString(R.string.dialog_message_error_cache_timeout_error))
                else -> CacheResult.Error(throwable.message)
            }
        }
    }
}


fun <ViewState> buildError(
    message: String,
    uiComponentType: UIComponentType,
    stateEvent: StateEvent?
): DataState<ViewState> =
    DataState(MessageModel(message, uiComponentType, MessageType.Error()), null, stateEvent, null)


fun <T : ViewState> createErrorToastDataState(text: String, stateEvent: StateEvent, returnAction: ReturnAction?): DataState<T> {
    val messageModel = MessageModel(text, UIComponentType.Toast(), MessageType.Error())
    return DataState(messageModel, null, stateEvent, returnAction)
}

fun <T : ViewState> createErrorDialogDataState(text: String, stateEvent: StateEvent, returnAction: ReturnAction?): DataState<T> {
    val messageModel = MessageModel(text, UIComponentType.Dialog(), MessageType.Error(), returnAction)
    return DataState(messageModel, null, stateEvent)
}

fun errorToastMessageModel(text: String): MessageModel =
    MessageModel(text, UIComponentType.Toast(), MessageType.Error())

fun successToastMessageModel(message: String): MessageModel =
    MessageModel(message, UIComponentType.Toast(), MessageType.Success())

@InternalCoroutinesApi
fun hasConnectivity(): Boolean {
    val connectivityManager =
        SpotifyApplication.sApplication.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkInfo = connectivityManager.activeNetworkInfo
    return networkInfo != null && networkInfo.isConnected
}