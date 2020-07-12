package com.sean.myapplication.screens.BaseClasses

import com.sean.spotifyapp.R
import com.sean.spotifyapp.getString
import kotlinx.coroutines.InternalCoroutinesApi

abstract class StateEvent{

    var showLoading: Boolean = false

    @InternalCoroutinesApi
    open fun errorString(): String = getString(R.string.dialog_message_error_default)



}
