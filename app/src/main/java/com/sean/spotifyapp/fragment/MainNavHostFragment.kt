package com.sean.spotifyapp.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.annotation.NavigationRes
import androidx.fragment.app.FragmentFactory
import androidx.navigation.fragment.NavHostFragment
import com.sean.myapplication.di.MainScope
import com.sean.spotifyapp.SpotifyApplication
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@InternalCoroutinesApi
class MainNavHostFragment : NavHostFragment() {

    @Inject
    lateinit var mainFragmentFactory: FragmentFactory


    override fun onAttach(context: Context) {
        (activity?.application as SpotifyApplication).mainComponent()
            .inject(this)

        SpotifyApplication.sApplication.mainComponent().inject(this)
        childFragmentManager.fragmentFactory = mainFragmentFactory
        super.onAttach(context)
    }

    companion object {
        const val KEY_GRAPH_ID = "android-support-nav:fragment:graphId"

        @JvmStatic
        fun create(
            @NavigationRes graphId: Int = 0
        ): MainNavHostFragment {
            var bundle: Bundle? = null
            if (graphId != 0) {
                bundle = Bundle()
                bundle.putInt(KEY_GRAPH_ID, graphId)
            }
            val result =
                MainNavHostFragment()
            if (bundle != null) {
                result.arguments = bundle
            }
            return result
        }
    }


}