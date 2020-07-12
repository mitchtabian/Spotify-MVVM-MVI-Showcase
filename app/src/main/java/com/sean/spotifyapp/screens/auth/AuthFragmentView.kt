package com.sean.spotifyapp.screens.auth

import android.content.Context
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import com.sean.myapplication.screens.BaseClasses.BaseFragmentView
import com.sean.myapplication.screens.BaseClasses.BaseViewModel
import com.sean.myapplication.screens.BaseClasses.ViewState
import com.sean.spotifyapp.R
import com.sean.spotifyapp.activity.AuthActivity
import com.sean.spotifyapp.getString
import com.sean.spotifyapp.screens.base.ReturnAction
import kotlinx.coroutines.InternalCoroutinesApi
@InternalCoroutinesApi
class AuthFragmentView(viewModel: AuthViewModel, context: Context) :
    BaseFragmentView<AuthViewState>(viewModel, context) {

    override fun onViewStateChanged(viewState: AuthViewState) {}

    override fun getLayoutRes(): Int = R.layout.fragment_auth

    override fun initViews(menu: Menu?) {
        setStateEvent(AuthStateEvent.AuthenticateSpotifyStateEvent(context as AuthActivity))
    }

    override fun resolveAction(action: ReturnAction) {
        when (action) {
            is AuthReturnAction.GoToMainActivityAction -> (context as AuthActivity).navMainActivity()
            is AuthReturnAction.ReShowSpotifyDialogAction ->
                setStateEvent(
                    AuthStateEvent.AuthenticateSpotifyStateEvent(context as AuthActivity)
                )
        }
    }

    override fun initTitle(): String = getString(R.string.toolbar_title_auth)
}