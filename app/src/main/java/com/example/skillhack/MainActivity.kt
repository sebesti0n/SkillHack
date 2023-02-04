package com.example.skillhack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowInsetsController
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        val x=WindowCompat.getInsetsController(window,window.decorView)
        x.systemBarsBehavior=WindowInsetsControllerCompat.BEHAVIOR_SHOW_BARS_BY_SWIPE
        //requestWindowFeature(Window.FEATURE_NO_TITLEZ
    }
}

