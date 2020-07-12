package com.sean.spotifyapp.screens.menu

import com.sean.myapplication.screens.BaseClasses.BaseViewModel
import com.sean.myapplication.screens.BaseClasses.StateEvent
import com.sean.spotifyapp.repository.MainRepository
import com.sean.spotifyapp.screens.base.DataState
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@InternalCoroutinesApi
class MenuViewModel
@Inject
constructor(val mainRepository: MainRepository) : BaseViewModel<MenuViewState>() {

    override fun getStateEventFlow(event: StateEvent): Flow<DataState<MenuViewState>> = when (event) {
        is MenuStateEvent.CategoriesClickedEvent -> {
            flow{
                emit(DataState<MenuViewState>(null, null, event, MenuReturnAction.GoToCategoriesAction()))
            }
        }

        is MenuStateEvent.SearchClickedEvent -> {
            flow{
                emit(DataState<MenuViewState>(null, null, event, MenuReturnAction.GoToSearchAction()))
            }
        }

        is MenuStateEvent.PlaylistsClickedEvent -> {
            flow{
                emit(DataState<MenuViewState>(null, null, event, MenuReturnAction.GoToPlaylistsAction()))
            }
        }

        is MenuStateEvent.LogoutEvent -> mainRepository.logout(event)

        else -> {
            flow {}
        }
    }

    override fun initViewState(): MenuViewState = MenuViewState()



}