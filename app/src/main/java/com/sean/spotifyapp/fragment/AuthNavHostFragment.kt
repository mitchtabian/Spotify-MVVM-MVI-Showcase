package com.sean.spotifyapp.fragment

import android.content.Context
import android.os.Bundle
import androidx.annotation.NavigationRes
import androidx.navigation.fragment.NavHostFragment
import com.sean.spotifyapp.SpotifyApplication
import kotlinx.coroutines.InternalCoroutinesApi
import javax.inject.Inject

@InternalCoroutinesApi
class AuthNavHostFragment: NavHostFragment(){

    @Inject
    lateinit var authFragmentFactory: AuthFragmentFactory

    override fun onAttach(context: Context) {
        super.onAttach(context)
        SpotifyApplication.sApplication.authComponent().inject(this)
        childFragmentManager.fragmentFactory = authFragmentFactory
    }

    companion object {
        private const val KEY_GRAPH_ID = "android-support-nav:fragment:graphId"

        @JvmStatic
        fun create(
            @NavigationRes graphId: Int = 0
        ): AuthNavHostFragment {
            var bundle: Bundle? = null
            if (graphId != 0) {
                bundle = Bundle()
                bundle.putInt(KEY_GRAPH_ID, graphId)
            }
            val result =
                AuthNavHostFragment()
            if (bundle != null) {
                result.arguments = bundle
            }
            return result
        }
    }


}