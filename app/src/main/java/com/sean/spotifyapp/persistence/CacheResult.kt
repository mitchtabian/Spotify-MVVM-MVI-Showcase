package com.sean.spotifyapp.persistence

sealed class CacheResult<out T> {

    data class Success<out T>(val data: T): CacheResult<T>()

    data class Error(val error: String?): CacheResult<Nothing>()



}