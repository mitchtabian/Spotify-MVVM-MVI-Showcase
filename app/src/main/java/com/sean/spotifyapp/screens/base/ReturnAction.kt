package com.sean.spotifyapp.screens.base

open class ReturnAction{

    private var hasBeenUsed: Boolean = false

    fun markActionUsed(){
        hasBeenUsed = true
    }

    fun hasBeenUsed(): Boolean = hasBeenUsed
}