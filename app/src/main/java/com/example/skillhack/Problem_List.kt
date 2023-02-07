package com.example.skillhack

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.UserHandle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class Problem_List : AppCompatActivity(){
    private lateinit var recyclerView: RecyclerView
    private lateinit var list: ArrayList<questions>
    private var db= Firebase.firestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_problem_list)
        //Log.d("TAG"," Problem list Running")

         recyclerView=findViewById<RecyclerView>(R.id.recylerview)

        recyclerView.layoutManager=LinearLayoutManager(this)

        list= arrayListOf()
        db = FirebaseFirestore.getInstance()
        db.collection("problemStructure").get().addOnSuccessListener {
            if(!it.isEmpty){
                for(data in it.documents) {
                    val ques: questions? = data.toObject(questions::class.java)
                    if (ques != null) {
                        list.add(ques)
                    }
                }
                recyclerView.adapter = problemListAdapter(list)
            }
        }
            .addOnFailureListener{
                Toast.makeText(this,it.toString(),Toast.LENGTH_SHORT).show()
            }
        problemListAdapter(list).onItemClick ={
            val i = Intent(this,question_popup::class.java)
            //i.putExtra("question",it)
            startActivity(i)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }



    }


}