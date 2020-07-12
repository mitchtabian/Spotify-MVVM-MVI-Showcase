package com.sean.spotifyapp.screens.categories

import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.sean.myapplication.screens.BaseClasses.*
import com.sean.spotifyapp.R
import com.sean.spotifyapp.screens.search.SearchViewModel
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class CategoriesFragment(
    viewModelFactory: ViewModelProvider.Factory
) : BaseFragment(viewModelFactory) {

    private val viewModel: CategoriesViewModel by viewModels {viewModelFactory}

    override fun getToolbarMenu(): Int = R.menu.menu_categories

    override fun initBaseView(): BaseFragmentView<ViewState> =
        context?.let { CategoriesFragmentView(viewModel, it) } as BaseFragmentView<ViewState>

}