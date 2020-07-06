package com.sean.spotifyapp.screens.search

import android.os.Parcelable
import com.sean.myapplication.screens.BaseClasses.ViewState
import com.sean.spotifyapp.models.Artist

class SearchViewState(var artists: MutableList<Artist>, var query: String, var resultOffset: Int) : ViewState()