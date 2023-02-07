package com.example.skillhack

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class  admin_question_list : AppCompatActivity(),Listener{
    private lateinit var recyclerView: RecyclerView
    private lateinit var list: ArrayList<questions>
    private var db= Firebase.firestore
    @SuppressLint("MissingInflatedId")
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
                    val ques: questions? = data.toObject(questions::class.java)
                    if(ques != null){
                        list.add(ques)
                    }
                }
                recyclerView.adapter = problemListAdapter(list,this)
            }
        }
            .addOnFailureListener{
                Toast.makeText(this,it.toString(),Toast.LENGTH_SHORT).show()
            }
    }

    override fun onitemclicklistener(position: Int) {
//        val intent = Intent(this,question_popup::class.java)
//        intent.putExtra("small",list[position].shortDiscriptionQuestion)
//        intent.putExtra("long",list[position].fullQuestion)
//        intent.putExtra("skill",list[position].skill)
//        intent.putExtra("lastdate",list[position].lastDate)
//        intent.putExtra("reward",list[position].rewardAmt)
//        startActivity(intent)
    }
}