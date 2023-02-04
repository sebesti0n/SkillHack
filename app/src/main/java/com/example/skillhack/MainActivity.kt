package com.example.skillhack

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
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
        supportActionBar?.hide()
        val x=WindowCompat.getInsetsController(window,window.decorView)
        x.systemBarsBehavior=WindowInsetsControllerCompat.BEHAVIOR_SHOW_BARS_BY_SWIPE
        //requestWindowFeature(Window.FEATURE_NO_TITLEZ
        val head=findViewById<TextView>(R.id.heading)
        head.alpha=0f
        typerfunc()
        head.animate().setDuration(3000).alpha(1f).withEndAction {
            val i = Intent(this, tell_ur_name::class.java)
            startActivity(i)
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
    }
}

