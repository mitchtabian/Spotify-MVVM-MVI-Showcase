package com.sean.spotifyapp.network

import com.sean.spotifyapp.models.responses.BaseSpotifyResponse
import com.sean.spotifyapp.persistence.CacheResult

sealed class ApiResult<out T>: BaseSpotifyResponse() {

    data class Success<T>(val data: T) : ApiResult<T>()

    data class GenericError(val status: Int,
                            val message: String
    ):ApiResult<Nothing>()

}