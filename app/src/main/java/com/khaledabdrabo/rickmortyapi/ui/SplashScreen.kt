package com.khaledabdrabo.rickmortyapi.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.khaledabdrabo.rickmortyapi.R
import com.khaledabdrabo.rickmortyapi.ui.character.CharacterListActivity

class SplashScreen : AppCompatActivity() {

    private val SPLASH_TIME_OUT:Long = 3000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed({
            startActivity(Intent(this, CharacterListActivity::class.java))
            finish()
        }, SPLASH_TIME_OUT)
    }

}