package com.example.skillhack


import android.content.Intent
import android.os.Build
import android.os.Build.VERSION.SDK_INT
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.auth.User



class question_popup : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question_popup)
        Log.d("TAG","entered in popup")
//        val question:questions?= intent.getParcelableExtra("question",questions::class.java)
        val small=intent.getStringExtra("small")
        val large=intent.getStringExtra("long")
        val skill=intent.getStringExtra("skill")
        val lastdate=intent.getStringExtra("lastdate")
        val reward=intent.getStringExtra("reward")




        Log.d("TAG","entered in if")
            Log.d("TAG","entered in if")
            val short=findViewById<TextView>(R.id.one_line)
            val long=findViewById<TextView>(R.id.mul_line)
            val date=findViewById<TextView>(R.id.datelast)
            val skil=findViewById<TextView>(R.id.skill)
        val urldrive=findViewById<EditText>(R.id.edtUrl)
        val rwd=findViewById<TextView>(R.id.rew)
            skil.text="skill: $skill"
            short.text=small.toString()
            long.text=large.toString()
           rwd.text="Reward: $reward"
          date.text="Last Date: $lastdate"
            //datenrew.text="Result will be live by $question.lastDate. \nTop problem solver will win cash rewards of Rs. $question.rewardAmt"
            val startsolving=findViewById<Button>(R.id.startsolving)


            startsolving.setOnClickListener {
                if (urldrive.text.trim().toString().isEmpty()){
                Toast.makeText(this@question_popup,"Enter drive Link",Toast.LENGTH_LONG).show()
            }
                else {
                    Toast.makeText(
                        this@question_popup,
                        "Submitted  Keep Solving!!",
                        Toast.LENGTH_LONG
                    )
                        .show()
                    val i = Intent(this, Problem_List::class.java)
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                    startActivity(i)
                    finish()
                }
            }
        }


}


