package com.sean.spotifyapp.screens.search.views

import android.content.Context
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.sean.spotifyapp.R
import com.sean.spotifyapp.models.Artist

class SearchResultView(context: Context) : FrameLayout(context){

    private var title: TextView
    private var image: ImageView


    init {
        View.inflate(context, R.layout.view_search_result, this)
        title = findViewById(R.id.TextView_search_result_name)
        image = findViewById(R.id.ImageView_search_result)
    }


    fun setArtist(artist: Artist){
        title.text = artist.name
        if(artist.images.isNotEmpty()){
            Glide.with(context).load(artist.images[0].url).into(image)
        }
    }






}