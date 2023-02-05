package com.example.skillhack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Problem_List : AppCompatActivity(), problemItemClicked {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_problem_list)
        val recyclerview=findViewById<RecyclerView>(R.id.recylerview)
        recyclerview.layoutManager=LinearLayoutManager(this)
        val lists=fetchData()
        val adapter = problemListAdapter(lists,this)
        recyclerview.adapter=adapter
    }
    private fun fetchData():ArrayList<String>{
        val list=ArrayList<String>()
        for (i in 0 until 100){
            list.add("Problem $i")
        }
        return list
    }

    override fun onItemClicked(item: String) {
        Toast.makeText(this,"clicked item",Toast.LENGTH_SHORT).show()
    }
}