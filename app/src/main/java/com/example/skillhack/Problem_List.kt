package com.example.skillhack

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Problem_List : AppCompatActivity(),Listener {
    private lateinit var recyclerView: RecyclerView
    private lateinit var list: ArrayList<questions>
    private var db= Firebase.firestore
    private lateinit var dashperson:ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_problem_list)
        //Log.d("TAG"," Problem list Running")
        dashperson=findViewById(R.id.dashboardIcon)

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
                recyclerView.adapter = problemListAdapter(list,this)
            }
        }
            .addOnFailureListener{
                Toast.makeText(this,it.toString(),Toast.LENGTH_SHORT).show()
            }

        val problemadapter=problemListAdapter(list,this)
        problemadapter.onItemClick ={
            val intent = Intent(this,question_popup::class.java)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            //i.putExtra("question",it)
            startActivity(intent)
        }
        dashperson.setOnClickListener{
            Log.d("TAG"," dash board opening")
            val i = Intent(this,Dashboard::class.java)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            //i.putExtra("question",it)
            startActivity(i)
            finish()
        }




    }


    @RequiresApi(Build.VERSION_CODES.S)
    override fun onitemclicklistener(position: Int) {

        //Third implementation (left in between)

//        val showpopup = PopUpFragment()
//        showpopup.show((activity as AppCompatActivity).supportFragmentManager,"showpopup")

        //second implementation

//        val small=list[position].shortDiscriptionQuestion
//        val large=list[position].fullQuestion
//        val skill=list[position].skill
//        val lastdate=list[position].lastDate
//        val reward=list[position].rewardAmt
//
//
//
//        val short=findViewById<TextView>(R.id.one_line)
//        val long=findViewById<TextView>(R.id.mul_line)
//        val datenrew=findViewById<TextView>(R.id.datenrew)
//        val skil=findViewById<TextView>(R.id.skill)
//
//        skil.text=skill.toString()
//        short.text=small.toString()
//        long.text=large.toString()
//
//        val dilogbind = layoutInflater.inflate(R.layout.activity_question_popup,null)
//        Log.d("tag","phir se pahoch gaye")
//        val mydilog=Dialog(this)
//        mydilog.setContentView(dilogbind)
//        mydilog.setCancelable(true)
//        mydilog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))


        //mydilog.show()

        // first implementation (working)

        val intent = Intent(this,question_popup::class.java)
        intent.putExtra("small",list[position].shortDiscriptionQuestion)
        intent.putExtra("long",list[position].fullQuestion)
        intent.putExtra("skill",list[position].skill)
        intent.putExtra("lastdate",list[position].lastDate)
        intent.putExtra("reward",list[position].rewardAmt)
        startActivity(intent)
    }


}