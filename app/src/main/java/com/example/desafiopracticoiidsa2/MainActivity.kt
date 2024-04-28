package com.example.desafiopracticoiidsa2

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.spotify.sdk.android.auth.AuthorizationClient
import com.spotify.sdk.android.auth.AuthorizationRequest
import com.spotify.sdk.android.auth.AuthorizationResponse

class MainActivity : AppCompatActivity() {

    // Authentication process
    val REQUEST_CODE = 1337

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = ContextCompat.getColor(this, R.color.white)
        }
        authenticateWithSpotify()
    }

    private fun authenticateWithSpotify() {
        val builder = AuthorizationRequest.Builder(
            Globals.CLIENT_ID, AuthorizationResponse.Type.TOKEN, Globals.REDIRECT_URI
        )
        builder.setScopes(
            arrayOf(
                "user-top-read",
                "user-read-recently-played",
                "user-library-read",
                "user-read-private"
            )
        )

        val request = builder.build()

        AuthorizationClient.openLoginActivity(this, REQUEST_CODE, request)
    }

    private fun setToken(token: String) {
        println(token)
        val sharedPreferences = getSharedPreferences(Globals.SHARED_PREFERENCES, MODE_PRIVATE)
        sharedPreferences.run {
            edit().putString(Globals.TOKEN, token)
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE) {
            val response = AuthorizationClient.getResponse(resultCode, data)

            when (response.type) {
                AuthorizationResponse.Type.TOKEN -> {
                    setToken(response.accessToken)
                }

                AuthorizationResponse.Type.ERROR -> {
                    Toast.makeText(
                        this, "Couldn't authorize app with Spotify credentials", Toast.LENGTH_LONG
                    ).show()
                }

                else -> {
                    Toast.makeText(
                        this, "Auth response received is invalid", Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }
}