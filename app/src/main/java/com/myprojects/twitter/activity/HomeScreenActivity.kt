package com.myprojects.twitter.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.myprojects.twitter.R
import com.myprojects.twitter.fragment.HomeScreenFragment

class HomeScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)

        val transaction =supportFragmentManager.beginTransaction()
        transaction.add(R.id.home_screen_container, HomeScreenFragment.newInstance())
        transaction.commit()
    }
}