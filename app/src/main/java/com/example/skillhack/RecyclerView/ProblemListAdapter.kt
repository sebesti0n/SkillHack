package com.example.skillhack.RecyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.skillhack.R

class problemListAdapter(val list: List<String>): RecyclerView.Adapter<problemListAdapter.problemListViewHolder>() {

    class problemListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val probView:TextView=itemView.findViewById(R.id.prob)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): problemListViewHolder {
      val view=LayoutInflater
          .from(parent.context)
          .inflate(R.layout.itemproblem,parent,false)

//            view.accessibilityDelegate= Accessibility

        val viewHolder = problemListViewHolder(view)


        return viewHolder
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: problemListViewHolder, position: Int) {
        val currProb=list[position]
        holder.probView.text=currProb
        //onclicklistener
    }

}



//interface problemItemClicked{
//    fun onItemClicked(item:String)
//}