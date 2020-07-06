package com.sean.spotifyapp.screens.base.views

import android.content.Context
import android.view.View
import android.widget.FrameLayout
import com.sean.spotifyapp.R

class LoadingView(context: Context): FrameLayout(context) {


    init {
        View.inflate(context, R.layout.view_loading, this)
        visibility = GONE
    }




}