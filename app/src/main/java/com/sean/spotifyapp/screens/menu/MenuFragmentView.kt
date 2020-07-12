package com.sean.spotifyapp.screens.menu

import android.content.Context
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.sean.myapplication.screens.BaseClasses.BaseFragmentView
import com.sean.myapplication.screens.BaseClasses.BaseViewModel
import com.sean.myapplication.screens.BaseClasses.ViewState
import com.sean.spotifyapp.R
import com.sean.spotifyapp.activity.MainActivity
import com.sean.spotifyapp.getString
import com.sean.spotifyapp.screens.base.ReturnAction
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class MenuFragmentView(viewModel: MenuViewModel, context: Context) :
    BaseFragmentView<MenuViewState>(viewModel, context) {

    override fun onOptionsItemSelected(item: MenuItem) {
        when (item.itemId) {
            R.id.menu_logout -> {
                setStateEvent(MenuStateEvent.LogoutEvent(context))
            }
        }
    }

    override fun onViewStateChanged(viewState: MenuViewState) {}

    override fun getLayoutRes(): Int = R.layout.fragment_menu

    override fun initViews(menu: Menu?) {
        findViewById<Button>(R.id.Button_menu_browse_categories).setOnClickListener {
            setStateEvent(MenuStateEvent.CategoriesClickedEvent())
        }
        findViewById<Button>(R.id.Button_menu_search_artists).setOnClickListener {
            setStateEvent(MenuStateEvent.SearchClickedEvent())
        }
        findViewById<Button>(R.id.Button_menu_my_playlists).setOnClickListener {
            setStateEvent(MenuStateEvent.PlaylistsClickedEvent())
        }
    }


    override fun resolveAction(action: ReturnAction) {

        when (action) {
            is MenuReturnAction.GoToCategoriesAction -> navigate(R.id.action_menuFragment_to_categoriesFragment)
            is MenuReturnAction.GoToSearchAction -> navigate(R.id.action_menuFragment_to_searchFragment)
            is MenuReturnAction.GoToPlaylistsAction -> navigate(R.id.action_menuFragment_to_playlistsFragment)
            is MenuReturnAction.LogoutAction -> (context as MainActivity).navigateToAuthActivity()
        }
    }

    override fun initTitle(): String = getString(R.string.toolbar_title_menu)
}
