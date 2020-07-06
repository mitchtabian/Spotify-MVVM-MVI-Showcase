package com.sean.spotifyapp.screens.menu

import com.sean.spotifyapp.screens.base.ReturnAction

sealed class MenuReturnAction: ReturnAction() {

    class GoToCategoriesAction: MenuReturnAction()
    class GoToSearchAction: MenuReturnAction()
    class GoToPlaylistsAction: MenuReturnAction()
    class LogoutAction: MenuReturnAction()

}