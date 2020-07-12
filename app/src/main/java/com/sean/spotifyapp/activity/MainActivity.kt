package com.sean.spotifyapp.activity

import android.content.Intent
import android.os.Bundle
import android.se.omapi.Session
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentFactory
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.sean.myapplication.di.MainScope
import com.sean.spotifyapp.R
import com.sean.spotifyapp.SpotifyApplication
import com.sean.spotifyapp.fragment.MainFragmentFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.InternalCoroutinesApi
import javax.inject.Inject

@FlowPreview
@ExperimentalCoroutinesApi
class MainActivity: AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    @InternalCoroutinesApi
    fun navigateToAuthActivity(){
        (application as SpotifyApplication).releaseMainComponent()
            startActivity(Intent(this, AuthActivity::class.java))
            finish()
    }

}
