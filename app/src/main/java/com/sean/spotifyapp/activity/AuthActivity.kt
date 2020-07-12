package com.sean.spotifyapp.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sean.spotifyapp.R
import com.sean.spotifyapp.SpotifyApplication
import com.spotify.sdk.android.auth.AuthorizationClient
import com.spotify.sdk.android.auth.AuthorizationRequest
import com.spotify.sdk.android.auth.AuthorizationResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.channels.Channel

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
class AuthActivity : AppCompatActivity() {

    companion object{
        const val SPOTIFY_AUTH_REQUEST_CODE = 1337
    }

    private var mChannel: Channel<AuthorizationResponse>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
//        SpotifyApplication.sApplication.appComponent.inject(this)
        SpotifyApplication.sApplication.authComponent().inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
    }

    @InternalCoroutinesApi
    override fun onDestroy() {
        super.onDestroy()
        SpotifyApplication.sApplication.releaseAuthComponent()
    }


    fun navMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun showSpotifyLogin(channel: Channel<AuthorizationResponse>) {
        val builder = AuthorizationRequest.Builder(
            resources.getString(R.string.spotify_client_ID),
            AuthorizationResponse.Type.TOKEN,
            resources.getString(R.string.spotify_redirect_uri)
        )
        builder.setScopes(arrayOf("streaming"))
        val request = builder.build()
        mChannel = channel
        AuthorizationClient.openLoginActivity(this, SPOTIFY_AUTH_REQUEST_CODE, request)
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        intent: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, intent)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                SPOTIFY_AUTH_REQUEST_CODE -> resolveSpotifyAuth(resultCode, intent)
            }
        }
    }

    private fun resolveSpotifyAuth(resultCode: Int, intent: Intent?) {
        val response =
            AuthorizationClient.getResponse(resultCode, intent)
        mChannel?.offer(response)
        mChannel = null
    }

}