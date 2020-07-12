package com.sean.spotifyapp.screens.menu

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.sean.myapplication.screens.BaseClasses.*
import com.sean.spotifyapp.R
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class MenuFragment
constructor(viewModelFactory: ViewModelProvider.Factory) :
    BaseFragment(viewModelFactory) {

    private val viewModel: MenuViewModel by viewModels {viewModelFactory}

    override fun getToolbarMenu(): Int = R.menu.menu_menu

    override fun initBaseView(): BaseFragmentView<ViewState> =
        context?.let { MenuFragmentView(viewModel, it) } as BaseFragmentView<ViewState>


}