package com.sean.spotifyapp.fragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.lifecycle.ViewModelProvider
import com.sean.spotifyapp.screens.auth.AuthFragment
import kotlinx.coroutines.InternalCoroutinesApi
import javax.inject.Inject
@InternalCoroutinesApi
class AuthFragmentFactory
@Inject
constructor(
    private val viewModelFactory: ViewModelProvider.Factory
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        when (className) {
            AuthFragment::class.java.name -> return AuthFragment(viewModelFactory)
        }
        return super.instantiate(classLoader, className)
    }
}