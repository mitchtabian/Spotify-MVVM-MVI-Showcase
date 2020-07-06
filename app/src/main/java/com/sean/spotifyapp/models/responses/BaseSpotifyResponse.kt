package com.sean.spotifyapp.models.responses

import com.sean.spotifyapp.network.SpotifyError

open class BaseSpotifyResponse{

    var error: SpotifyError? = null

}