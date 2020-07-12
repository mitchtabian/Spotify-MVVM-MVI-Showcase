package com.sean.spotifyapp.repository

import com.sean.myapplication.screens.BaseClasses.StateEvent
import com.sean.spotifyapp.network.MessageModel
import com.sean.spotifyapp.network.MessageType
import com.sean.spotifyapp.network.UIComponentType
import com.sean.spotifyapp.persistence.CacheResult
import com.sean.spotifyapp.screens.base.DataState
import kotlinx.coroutines.InternalCoroutinesApi

abstract class CacheResponseHandler<ViewState, Data>(
    private val cacheResult: CacheResult<Data>,
    private val stateEventToBeResolved: StateEvent?
) {

    @InternalCoroutinesApi
    suspend fun getResult(): DataState<ViewState> {
        return when (cacheResult) {
            is CacheResult.Error -> {
                DataState<ViewState>(
                    MessageModel(
                        message = "${stateEventToBeResolved?.errorString()}\n\nReason: ${cacheResult.error}",
                        uiComponentType = UIComponentType.Dialog(),
                        messageType = MessageType.Error()
                    ), null, stateEventToBeResolved
                )
            }
            is CacheResult.Success -> {
                if (cacheResult.data == null) {
                    DataState<ViewState>(
                        MessageModel(
                            message = "${stateEventToBeResolved?.errorString()}\n\nReason: Data is NULL.",
                            uiComponentType = UIComponentType.Dialog(),
                            messageType = MessageType.Error()
                        ), null, stateEventToBeResolved
                    )
                } else {
                    handleSuccess(cacheResult.data, stateEventToBeResolved)
                }
            }
        }
    }

    abstract suspend fun handleSuccess(
        resultObj: Data,
        stateEvent: StateEvent?
    ): DataState<ViewState>

}