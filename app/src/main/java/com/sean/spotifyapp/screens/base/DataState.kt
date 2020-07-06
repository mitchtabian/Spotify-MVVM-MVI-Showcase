package com.sean.spotifyapp.screens.base

import com.sean.myapplication.screens.BaseClasses.StateEvent
import com.sean.spotifyapp.network.MessageModel

class DataState<T>(
    val messageModel: MessageModel? = null,
    val data: T? = null,
    val stateEvent: StateEvent? = null,
    val returnAction: ReturnAction? = null
){
    fun isJobCompleted(): Boolean = stateEvent != null
}