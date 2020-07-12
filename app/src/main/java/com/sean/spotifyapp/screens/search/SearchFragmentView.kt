package com.sean.spotifyapp.screens.search

import android.app.SearchManager
import android.content.Context
import android.view.Menu
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sean.myapplication.screens.BaseClasses.BaseFragmentView
import com.sean.spotifyapp.R
import com.sean.spotifyapp.getString
import com.sean.spotifyapp.screens.search.views.SearchResultAdapter
import kotlinx.coroutines.InternalCoroutinesApi
@InternalCoroutinesApi
class SearchFragmentView(val viewModel: SearchViewModel, context: Context) :
    BaseFragmentView<SearchViewState>(viewModel, context){

    private lateinit var recyclerView: RecyclerView
    private var adapter: SearchResultAdapter? = null
    private lateinit var searchView: SearchView


    override fun onViewStateChanged(viewState: SearchViewState) {
        adapter?.submitList(viewState.artists, false)
        searchView.findViewById<EditText>(R.id.search_src_text).setText(viewState.query)
        if(viewModel.scrollPosition != null) {
            recyclerView.layoutManager?.onRestoreInstanceState(viewModel.scrollPosition)
        }
    }

    override fun getLayoutRes(): Int = R.layout.fragment_search

    override fun initViews(menu: Menu?) {
        recyclerView = findViewById(R.id.RecyclerView_search_results)
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if(getCurrentViewState().artists.size > 0)
                onScrollChanged()
            }
        })
        adapter = SearchResultAdapter()
        recyclerView.adapter = adapter
        initSearchView(menu)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        saveScrollPosition()
    }

    override fun initTitle(): String = getString(R.string.toolbar_title_search_artists)

    private fun onScrollChanged() {
        val layoutManager = recyclerView.layoutManager as LinearLayoutManager
        val lastPosition = layoutManager.findLastVisibleItemPosition()
        if (lastPosition == adapter?.itemCount?.minus(1)) {
            val currentQuery = searchView.findViewById<EditText>(R.id.search_src_text).text.toString()
            val viewState = getCurrentViewState()
            setStateEvent(
                SearchStateEvent.SearchArtistsEvent(
                    currentQuery,
                    viewState.artists,
                    viewState.resultOffset
                )
            )
        }
    }

    private fun initSearchView(menu: Menu?) {

        val searchManager: SearchManager = context.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView = menu?.findItem(R.id.action_search)?.actionView as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo((context as AppCompatActivity).componentName))
        searchView.maxWidth = Integer.MAX_VALUE
        searchView.setIconifiedByDefault(true)
        searchView.isSubmitButtonEnabled = true

        val searchPlate = searchView.findViewById<EditText>(R.id.search_src_text)
        searchPlate.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_UNSPECIFIED
                || actionId == EditorInfo.IME_ACTION_SEARCH
            ) {
                val searchQuery = v.text.toString()
                saveScrollPosition()
                setStateEvent(SearchStateEvent.SearchArtistsEvent(searchQuery, getCurrentViewState().artists, 0))
            }
            true
        }

        searchView.findViewById<View>(R.id.search_go_btn).setOnClickListener {
            val searchQuery = searchPlate.text.toString()
            saveScrollPosition()
            setStateEvent(SearchStateEvent.SearchArtistsEvent(searchQuery, getCurrentViewState().artists, 0))
        }
    }

    private fun saveScrollPosition(){
        viewModel.scrollPosition = recyclerView.layoutManager?.onSaveInstanceState()
    }

}