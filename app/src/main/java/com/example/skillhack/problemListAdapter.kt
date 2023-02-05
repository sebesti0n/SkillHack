package com.example.skillhack

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class problemListAdapter(private val probs:ArrayList<String>, private val listner:problemItemClicked): RecyclerView.Adapter<probsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): probsViewHolder {
      val view=LayoutInflater.from(parent.context).inflate(R.layout.itemproblem,parent,false)
        val viewHolder =probsViewHolder(view)
        view.setOnClickListener{
listner.onItemClicked(probs[viewHolder.adapterPosition])
        }

        return viewHolder
    }

    override fun getItemCount(): Int {
        return probs.size
    }

    override fun onBindViewHolder(holder: probsViewHolder, position: Int) {
        val currProb=probs[position]
        holder.probView.text=currProb
    }

}
class probsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
 val probView:TextView=itemView.findViewById(R.id.prob)
}


interface problemItemClicked{
    fun onItemClicked(item:String)
}