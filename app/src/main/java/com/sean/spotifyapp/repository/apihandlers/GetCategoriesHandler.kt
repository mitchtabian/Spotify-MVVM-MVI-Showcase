package com.sean.spotifyapp.repository.apihandlers

import com.sean.myapplication.screens.BaseClasses.StateEvent
import com.sean.spotifyapp.R
import com.sean.spotifyapp.getString
import com.sean.spotifyapp.models.CategoryItem
import com.sean.spotifyapp.models.PlaylistItem
import com.sean.spotifyapp.models.responses.CategoriesResponse
import com.sean.spotifyapp.models.responses.PlaylistsResponse
import com.sean.spotifyapp.network.ApiService
import com.sean.spotifyapp.persistence.MainDao
import com.sean.spotifyapp.repository.apihandlers.base.CacheRetrievingApiHandler
import com.sean.spotifyapp.repository.successToastMessageModel
import com.sean.spotifyapp.screens.base.DataState
import com.sean.spotifyapp.screens.categories.CategoriesViewState
import com.sean.spotifyapp.screens.playlists.PlaylistsViewState
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class GetCategoriesHandler(
    private val stateEvent: StateEvent,
    private val apiService: ApiService,
    private val mainDao: MainDao,
    private val tokenString: suspend () -> String
) : CacheRetrievingApiHandler<CategoriesResponse, List<CategoryItem>, CategoriesViewState>(stateEvent,
    apiCall = { apiService.getCategories(tokenString.invoke()) },
    cacheCall = { mainDao.getAllCategories() }) {


    override fun createSuccessViewStateFromCache(
        data: List<CategoryItem>,
        stateEvent: StateEvent?
    ): DataState<CategoriesViewState> {
        return DataState(
            null,
            CategoriesViewState(data),
            stateEvent,
            null
        )
    }

    override suspend fun saveToCache(data: CategoriesResponse) {
        val categories = data.categories.items
        for (category in categories) {
            mainDao.insertCategory(category)
        }
    }

}