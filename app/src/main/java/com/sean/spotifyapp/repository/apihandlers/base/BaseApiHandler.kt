package com.sean.spotifyapp.repository.apihandlers.base

import android.util.Log
import com.google.gson.Gson
import com.sean.myapplication.screens.BaseClasses.StateEvent
import com.sean.spotifyapp.R
import com.sean.spotifyapp.getString
import com.sean.spotifyapp.network.*
import com.sean.spotifyapp.repository.buildError
import com.sean.spotifyapp.repository.hasConnectivity
import com.sean.spotifyapp.screens.base.DataState
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout
import retrofit2.HttpException
import java.io.IOException
@InternalCoroutinesApi
abstract class BaseApiHandler<ViewState, NetworkObj> constructor(
    private val stateEvent: StateEvent,
    private val apiCall: suspend () -> NetworkObj
) {

    open val result = flow {
        val apiResult = safeApiCall()
        emit(resolveApiResult(apiResult))

    }

    protected suspend fun resolveApiResult(
        apiResult: ApiResult<NetworkObj>
    ): DataState<ViewState> {
        when (apiResult) {
            is ApiResult.GenericError -> {
                return buildError(
                    apiResult.message,
                    UIComponentType.Toast(),
                    stateEvent
                )
            }

            is ApiResult.Success ->
                return if (apiResult.data == null) {
                    buildError(
                        getString(R.string.dialog_message_error_default),
                        UIComponentType.Dialog(),
                        stateEvent
                    )
                } else {
                    handleApiCallSuccess(apiResult.data)
                }
        }
    }

    suspend fun safeApiCall(): ApiResult<NetworkObj> {
        if(!hasConnectivity()){
            return ApiResult.GenericError(69, getString(R.string.dialog_message_error_no_internet))
        }

        return withContext(IO) {
            try {
                withTimeout(1000) {
                    ApiResult.Success(apiCall.invoke())
                }
            } catch (throwable: Throwable) {
                throwable.printStackTrace()
                when (throwable) {
                    is TimeoutCancellationException -> {
                        val code = 408
                        ApiResult.GenericError(
                            code,
                            getString(R.string.dialog_message_error_timeout)
                        )
                    }
                    is IOException -> {
                        ApiResult.GenericError(
                            69,
                            getString(R.string.dialog_message_error_default)
                        )
                    }
                    is HttpException -> {
                        val spotifyError =
                            Gson().fromJson(convertErrorBody(throwable), ErrorResponse::class.java)
                        ApiResult.GenericError(
                            spotifyError.error.status,
                            spotifyError.error.message
                        )
                    }
                    else -> {
                        ApiResult.GenericError(
                            -1,
                            getString(R.string.dialog_message_error_default)
                        )
                    }
                }
            }
        }
    }



    private fun convertErrorBody(throwable: HttpException): String? {
        return try {
            throwable.response()?.errorBody()?.string()
        } catch (exception: Exception) {
            getString(R.string.dialog_message_error_timeout)
        }
    }

    abstract suspend fun handleApiCallSuccess(data: NetworkObj): DataState<ViewState>


}