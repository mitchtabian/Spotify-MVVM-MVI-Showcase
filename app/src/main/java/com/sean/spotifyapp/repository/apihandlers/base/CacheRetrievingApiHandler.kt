package com.sean.spotifyapp.repository.apihandlers.base

import android.util.Log
import com.sean.myapplication.screens.BaseClasses.StateEvent
import com.sean.spotifyapp.network.ApiResult
import com.sean.spotifyapp.repository.CacheResponseHandler
import com.sean.spotifyapp.repository.safeCacheCall
import com.sean.spotifyapp.screens.base.DataState
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.flow

@InternalCoroutinesApi
abstract class CacheRetrievingApiHandler<NetworkObject, CacheObject, ViewState> constructor(
    private val stateEvent: StateEvent,
    private val cacheCall: (suspend () -> CacheObject),
    apiCall: suspend () -> NetworkObject
) : CachingApiHandler<NetworkObject, CacheObject, ViewState>(stateEvent, apiCall) {


    override val result = flow {
        emit(returnCache(false))
        val apiResult = safeApiCall()
        emit(cacheResultAndQuery(apiResult))
    }

    private suspend fun cacheResultAndQuery(apiResult: ApiResult<NetworkObject>): DataState<ViewState> {
        return resolveApiResult(apiResult)
    }


    private suspend fun returnCache(markjobComplete: Boolean): DataState<ViewState> {
        val cacheResult = safeCacheCall(cacheCall)
        var jobCompleteMarker: StateEvent? = null

        if (markjobComplete) {
            jobCompleteMarker = stateEvent
        }
        return object :
            CacheResponseHandler<ViewState, CacheObject>(cacheResult, jobCompleteMarker) {
            override suspend fun handleSuccess(
                resultObj: CacheObject,
                stateEventToBeResolved: StateEvent?
            ): DataState<ViewState> {
                return createSuccessViewStateFromCache(resultObj, stateEventToBeResolved)
            }
        }.getResult()
    }

    override suspend fun createSuccessViewState(
        data: NetworkObject,
        stateEvent: StateEvent?
    ): DataState<ViewState> = returnCache(true)


    abstract fun createSuccessViewStateFromCache(
        data: CacheObject,
        stateEvent: StateEvent?
    ): DataState<ViewState>


}