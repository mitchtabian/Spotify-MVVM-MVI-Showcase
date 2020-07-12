package com.sean.spotifyapp

import kotlinx.coroutines.InternalCoroutinesApi


@InternalCoroutinesApi
fun getString(id: Int): String{
    return SpotifyApplication.sApplication.getString(id)
}