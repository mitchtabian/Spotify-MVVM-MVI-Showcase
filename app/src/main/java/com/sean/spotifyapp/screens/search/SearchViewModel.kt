package com.sean.spotifyapp.screens.search

import android.os.Parcelable
import com.sean.myapplication.screens.BaseClasses.BaseViewModel
import com.sean.myapplication.screens.BaseClasses.StateEvent
import com.sean.spotifyapp.repository.MainRepository
import com.sean.spotifyapp.screens.base.DataState
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
@InternalCoroutinesApi
class SearchViewModel
@Inject
constructor(val mainRepository: MainRepository) : BaseViewModel<SearchViewState>() {

    var scrollPosition: Parcelable? = null

    override fun getStateEventFlow(event: StateEvent): Flow<DataState<SearchViewState>> = when (event) {
        is SearchStateEvent.SearchArtistsEvent -> {
            mainRepository.searchArtists(event)
        }

        else -> {
            flow {}
        }

    }
    override fun initViewState(): SearchViewState {
        return SearchViewState(ArrayList(), "", 0)
    }



}