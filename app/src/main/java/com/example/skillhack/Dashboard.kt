package com.example.skillhack

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class Dashboard : AppCompatActivity() {
    private lateinit var userdet:UserDetails
    private lateinit var namee:TextView
    private lateinit var Mobile:TextView
    private lateinit var trwd:TextView
    private lateinit var TtlProb:TextView
    private lateinit var contactUS:TextView
    private lateinit var Tandc:TextView
    private lateinit var Signout: TextView
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
        auth=FirebaseAuth.getInstance()
        Signout.setOnClickListener{
            auth.signOut()
            val i = Intent(this,Login::class.java)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            //i.putExtra("question",it)
            startActivity(i)
            finish()
        }



        userdet= UserDetails()


//        fstore=FirebaseFirestore.getInstance()
        val userID=auth.currentUser!!.uid
//        fref=fstore.collection("users").document(userID)
        val mp: MutableMap<String, Any> = HashMap()
        val db = Firebase.firestore
        val docRef = db.collection("users").document(userID)
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    Log.d(TAG, "DocumentSnapshot data: ${document.data}")
                } else {
                    Log.d(TAG, "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "get failed with ", exception)
            }

//        fref.get()
//            .addOnSuccessListener { document->
//
//                    userdet = document.toObject()!!
//                Log.d("TAG"," dash board opened")
//            }
//        if(userdet!=null) {
//            namee.text = userdet.getname()
//            Mobile.text = userdet.getmobNum()
//            trwd.text = userdet.getTotalReward().toString()
//        }

    }
}