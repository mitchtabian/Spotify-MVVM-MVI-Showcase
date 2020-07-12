package com.sean.spotifyapp

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class MockTestRunner: AndroidJUnitRunner(){

    @ExperimentalCoroutinesApi
    override fun newApplication(
        cl: ClassLoader?,
        className: String?,
        context: Context?
    ) : Application {
        return super.newApplication(cl, TestBaseApplication::class.java.name, context)
    }
}