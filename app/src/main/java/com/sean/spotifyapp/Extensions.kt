package com.sean.spotifyapp



fun getString(id: Int): String{
    return SpotifyApplication.sApplication.getString(id)
}