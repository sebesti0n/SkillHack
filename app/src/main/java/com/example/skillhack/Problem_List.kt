package com.example.skillhack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Problem_List : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_problem_list)
        Log.d("TAG"," Problem list Running")

        val recyclerview=findViewById<RecyclerView>(R.id.recylerview)

        recyclerview.layoutManager=LinearLayoutManager(this)

        val lists=fetchData()

        val adapter = problemListAdapter(lists)

        recyclerview.adapter=adapter
    }
    private fun fetchData():List<String>{
        val list= mutableListOf<String>()
        for (i in 0 until 100){
            list.add("Problem $i")
        }
        return list
    }


}