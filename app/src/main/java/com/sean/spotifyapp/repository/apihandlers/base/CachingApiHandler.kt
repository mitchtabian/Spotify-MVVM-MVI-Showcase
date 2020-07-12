package com.sean.spotifyapp.repository.apihandlers.base

import com.sean.myapplication.screens.BaseClasses.StateEvent
import com.sean.spotifyapp.screens.base.DataState
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
abstract class CachingApiHandler<NetworkObj, CacheObj, ViewState> constructor(
    private val stateEvent: StateEvent,
    apiCall: suspend () -> NetworkObj
) : BaseApiHandler<ViewState, NetworkObj>(stateEvent, apiCall) {

    override suspend fun handleApiCallSuccess(data: NetworkObj): DataState<ViewState> {
        saveToCache(data)
        return createSuccessViewState(data, stateEvent)
    }

    abstract suspend fun createSuccessViewState(
        data: NetworkObj,
        stateEvent: StateEvent?
    ): DataState<ViewState>

    abstract suspend fun saveToCache(data: NetworkObj)



}