package com.sean.spotifyapp.di.module

import com.sean.spotifyapp.di.auth.AuthComponent
import com.sean.spotifyapp.di.main.MainComponent
import dagger.Module

@Module(subcomponents = [AuthComponent::class, MainComponent::class])
class SubComponentModule