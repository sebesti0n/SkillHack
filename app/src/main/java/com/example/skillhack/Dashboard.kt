package com.example.skillhack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject

class Dashboard : AppCompatActivity() {
    private lateinit var userdet:UserDetails
    private lateinit var namee:TextView
    private lateinit var Mobile:TextView
    private lateinit var trwd:TextView
    private lateinit var TtlProb:TextView
    private lateinit var contactUS:TextView
    private lateinit var Tandc:TextView
    private lateinit var Signout:TextView
    private lateinit var fstore:FirebaseFirestore
    private lateinit var auth:FirebaseAuth
    private lateinit var fref:DocumentReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        namee=findViewById(R.id.userName)
        Mobile=findViewById(R.id.MobUser)
        trwd=findViewById(R.id.cnt_rwd)
        TtlProb=findViewById(R.id.NoProblemSolved)
        contactUS=findViewById(R.id.contactUs)
        Tandc=findViewById(R.id.TandC)
        Signout=findViewById(R.id.Logout)



        userdet= UserDetails()
        auth=FirebaseAuth.getInstance()
        fstore=FirebaseFirestore.getInstance()
        val userID=auth.currentUser!!.uid
        fref=fstore.collection("users").document(userID)
        fref.get()
            .addOnSuccessListener { document->

                    userdet = document.toObject()!!
            }
        namee.text=userdet.getname()
        Mobile.text=userdet.getmobNum()
        trwd.text=userdet.getTotalReward().toString()

    }
}