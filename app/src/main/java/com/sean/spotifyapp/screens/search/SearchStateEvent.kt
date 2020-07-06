package com.sean.spotifyapp.screens.search

import com.sean.myapplication.screens.BaseClasses.StateEvent
import com.sean.spotifyapp.models.Artist

sealed class SearchStateEvent : StateEvent() {


    class SearchArtistsEvent(val query: String, val artists: MutableList<Artist>, val offset: Int) :
        SearchStateEvent()


}