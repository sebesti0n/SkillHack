package com.example.skillhack

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth

class checker : AppCompatActivity() {
    private lateinit var auth:FirebaseAuth
    private var SignOutBtn: Button=findViewById(R.id.SignOutBtn)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checker)
        auth=FirebaseAuth.getInstance()
        SignOutBtn.setOnClickListener{
            auth.signOut()
            startActivity(Intent(this,Login::class.java))
        }
    }
}