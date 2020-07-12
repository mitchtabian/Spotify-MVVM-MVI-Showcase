package com.sean.spotifyapp.screens.auth

import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.sean.myapplication.screens.BaseClasses.BaseFragment
import com.sean.myapplication.screens.BaseClasses.BaseFragmentView
import com.sean.myapplication.screens.BaseClasses.ViewState
import com.sean.spotifyapp.R
import com.sean.spotifyapp.screens.categories.CategoriesViewModel
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class AuthFragment(viewModelFactory: ViewModelProvider.Factory) : BaseFragment(viewModelFactory) {

    private val viewModel: AuthViewModel by viewModels {viewModelFactory}


    override fun getToolbarMenu(): Int = R.menu.menu_auth

    override fun initBaseView(): BaseFragmentView<ViewState> =
        context?.let { AuthFragmentView(viewModel, it) } as BaseFragmentView<ViewState>

}