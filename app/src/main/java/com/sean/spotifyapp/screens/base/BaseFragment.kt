package com.sean.myapplication.screens.BaseClasses

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.sean.spotifyapp.screens.menu.MenuViewModel


abstract class BaseFragment(val viewModelFactory: ViewModelProvider.Factory) : Fragment() {

    private lateinit var baseView: BaseFragmentView<ViewState>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        baseView = initBaseView()
        (context as AppCompatActivity).title = initTitle()
        return baseView
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(getToolbarMenu(), menu)
        baseView.onCreateOptionsMenu(menu)
        getToolbarMenu()
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        baseView.onOptionsItemSelected(item)
        return super.onOptionsItemSelected(item)
    }

    private fun initTitle(): String = baseView.initTitle()

    abstract fun getToolbarMenu(): Int

    abstract fun initBaseView(): BaseFragmentView<ViewState>


}