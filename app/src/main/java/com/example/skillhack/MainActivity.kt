package com.example.skillhack

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowInsetsController
import android.widget.ImageView
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
        val head=findViewById<TextView>(R.id.heading)
        val logo=findViewById<ImageView>(R.id.logo)
        head.alpha=0f
        logo.alpha=0f
        logo.animate().setDuration(3000).alpha(1f)
        typerfunc()
        head.animate().setDuration(3001).alpha(1f).withEndAction {
            val i = Intent(this, Problem_List::class.java)

            startActivity(i)

            Log.d("TAG","starting Problem list")
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }

    }

    private fun typerfunc() {
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

