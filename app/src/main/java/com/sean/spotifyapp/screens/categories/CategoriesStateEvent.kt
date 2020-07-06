package com.sean.spotifyapp.screens.categories

import com.sean.myapplication.screens.BaseClasses.StateEvent

sealed class CategoriesStateEvent: StateEvent(){

    class GetCategoriesEvent : CategoriesStateEvent(){
        init {
            showLoading = true
        }
    }



}