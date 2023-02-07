package com.example.skillhack

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView

class skill : AppCompatActivity() {
    private lateinit var s1:CardView
    private lateinit var s2:CardView

    private lateinit var s3:CardView
    private lateinit var s4:CardView
    private lateinit var s5:CardView
    private lateinit var  s6:CardView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_skill)
        s1=findViewById(R.id.cv1)
        s2=findViewById(R.id.cv2)
        s3=findViewById(R.id.cv3)
        s4=findViewById(R.id.cv4)
        s5=findViewById(R.id.cv5)
        s6=findViewById(R.id.cv6)
        s1.setOnClickListener{
            val intent = Intent(this,Problem_List::class.java)
            //i.putExtra("question",it)
            startActivity(intent)
        }
        s2.setOnClickListener{
            val intent = Intent(this,Problem_List::class.java)
            //i.putExtra("question",it)
            startActivity(intent)
        }
        s3.setOnClickListener{
            val intent = Intent(this,Problem_List::class.java)
            //i.putExtra("question",it)
            startActivity(intent)
        }
        s4.setOnClickListener{
            val intent = Intent(this,Problem_List::class.java)
            //i.putExtra("question",it)
            startActivity(intent)
        }
        s5.setOnClickListener{
            val intent = Intent(this,Problem_List::class.java)
            //i.putExtra("question",it)
            startActivity(intent)
        }
        s6.setOnClickListener{
            val intent = Intent(this,Problem_List::class.java)
            //i.putExtra("question",it)
            startActivity(intent)
        }
        }
    }
