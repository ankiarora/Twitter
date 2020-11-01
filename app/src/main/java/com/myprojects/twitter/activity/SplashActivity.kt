package com.myprojects.twitter.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.myprojects.twitter.R
import com.myprojects.twitter.Utils.Constants

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            val activityIntent = Intent(this, HomeScreenActivity::class.java)
            startActivity(activityIntent)
            finish()

        }, Constants.SPLASH_TIME_OUT)
    }
}