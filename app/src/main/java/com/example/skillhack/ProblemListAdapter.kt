package com.example.skillhack

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.getAttributionTag
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.withContext

class problemListAdapter(val list:ArrayList<questions>, private val listener: Listener): RecyclerView.Adapter<problemListAdapter.problemListViewHolder>() {

    var onItemClick: ((questions)->Unit)? =null

    inner class problemListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val shortdisc:TextView=itemView.findViewById(R.id.short_disc)
        val fulldisc:TextView=itemView.findViewById(R.id.full_disc)
        val skill:TextView=itemView.findViewById(R.id.skill)
        val reward:TextView=itemView.findViewById(R.id.reward)
        val lastdate:TextView=itemView.findViewById(R.id.lastdate)

        init{
            itemView.setOnClickListener{
                Log.d("TAG","click is working")
                listener.onitemclicklistener(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): problemListViewHolder {
      val view=LayoutInflater
          .from(parent.context)
          .inflate(R.layout.itemproblem,parent,false)

//            view.accessibilityDelegate= Accessibility

        val viewHolder =problemListViewHolder(view)
        return viewHolder
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: problemListViewHolder, position: Int) {
        holder.shortdisc.text=list[position].shortDiscriptionQuestion
        holder.reward.text=list[position].rewardAmt
        holder.lastdate.text=list[position].lastDate
        holder.skill.text=list[position].skill
        holder.fulldisc.text=list[position].fullQuestion
        //onclicklistener
//        val ques= list[position]
//        holder.itemView.setOnClickListener{
//           // Log.d("TAG","pressed")
//            onItemClick?.invoke(ques)
//        }
    }



}



//interface problemItemClicked{
//    fun onItemClicked(item:String)
//}