package com.example.skillhack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowInsetsController
import android.widget.TextView
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //SPLASH SCREEN CODE STARTS

        supportActionBar?.hide()
        val x=WindowCompat.getInsetsController(window,window.decorView)
        x.systemBarsBehavior=WindowInsetsControllerCompat.BEHAVIOR_SHOW_BARS_BY_SWIPE
        //requestWindowFeature(Window.FEATURE_NO_TITLEZ
        val typer=findViewById<TextView>(R.id.splash1)
        val label = "Hack a skill, earn rewards, land a job!!...."
        val stringbuilder = StringBuilder()

        Thread{
            for(letter in label){
                stringbuilder.append(letter)
                Thread.sleep(60)
                runOnUiThread{
                    typer.text= stringbuilder.toString()
                }
            }
        }.start()
        // SPLASH SCREEN CODE END




    }
}

