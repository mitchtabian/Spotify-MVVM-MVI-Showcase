package com.sean.spotifyapp.screens.categories

import android.content.Context
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.sean.myapplication.screens.BaseClasses.BaseFragmentView
import com.sean.myapplication.screens.BaseClasses.BaseViewModel
import com.sean.myapplication.screens.BaseClasses.ViewState
import com.sean.spotifyapp.R
import com.sean.spotifyapp.getString
import com.sean.spotifyapp.screens.base.ReturnAction
import com.sean.spotifyapp.screens.categories.views.CategoriesAdapter
import kotlinx.coroutines.InternalCoroutinesApi
@InternalCoroutinesApi
class CategoriesFragmentView(viewModel: CategoriesViewModel, context: Context) :
    BaseFragmentView<CategoriesViewState>(viewModel, context) {

    private lateinit var recyclerView: RecyclerView

    override fun onViewStateChanged(viewState: CategoriesViewState) {
        recyclerView.adapter = CategoriesAdapter(viewState.categories)
    }


    override fun getLayoutRes(): Int = R.layout.fragment_categories

    override fun initViews(menu: Menu?) {
        recyclerView = findViewById(R.id.RecyclerView_categories)
    }

    override fun initialAction() {
        setStateEvent(CategoriesStateEvent.GetCategoriesEvent())
    }

    @InternalCoroutinesApi
    override fun initTitle(): String = getString(R.string.toolbar_title_categories)
}