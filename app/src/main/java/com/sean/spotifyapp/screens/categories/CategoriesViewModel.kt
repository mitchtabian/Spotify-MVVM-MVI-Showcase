package com.sean.spotifyapp.screens.categories

import com.sean.myapplication.screens.BaseClasses.BaseViewModel
import com.sean.myapplication.screens.BaseClasses.StateEvent
import com.sean.spotifyapp.repository.MainRepository
import com.sean.spotifyapp.screens.base.DataState
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
@InternalCoroutinesApi
class CategoriesViewModel
@Inject
constructor(val mainRepository: MainRepository) :
    BaseViewModel<CategoriesViewState>() {


    override fun getStateEventFlow(event: StateEvent): Flow<DataState<CategoriesViewState>> = when (event) {
        is CategoriesStateEvent.GetCategoriesEvent -> mainRepository.getCategories(event)
        else -> {
            flow {}
        }
    }

    override fun initViewState(): CategoriesViewState = CategoriesViewState(ArrayList())

}