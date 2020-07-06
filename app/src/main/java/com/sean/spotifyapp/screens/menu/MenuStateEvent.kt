package com.sean.spotifyapp.screens.menu

import android.content.Context
import com.sean.myapplication.screens.BaseClasses.StateEvent

sealed class MenuStateEvent: StateEvent(){

    class CategoriesClickedEvent: MenuStateEvent()

    class SearchClickedEvent : MenuStateEvent()

    class PlaylistsClickedEvent : MenuStateEvent()

    class LogoutEvent(val context: Context) : MenuStateEvent()

}