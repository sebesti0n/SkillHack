package com.example.skillhack

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
                for(data in it.documents){
                    val user: questions? = data.toObject(questions::class.java)
                    if(user != null){
                        list.add(user)
                    }
                }
                recyclerView.adapter = problemListAdapter(list)
            }
        }
            .addOnFailureListener{
                Toast.makeText(this,it.toString(),Toast.LENGTH_SHORT).show()
            }

       // val lists=fetchData()

//        val adapter = problemListAdapter(lists)
//
//        recyclerview.adapter=adapter
    }
    private fun fetchData():List<String>{
        val list= mutableListOf<String>()
        for (i in 0 until 100){
            list.add("Problem $i")
        }
        return list
    }


}