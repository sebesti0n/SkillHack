package admin_ques

import com.example.skillhack.R
import com.example.skillhack.questions
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class admin_ques_adapter(private val list :ArrayList<questions>): RecyclerView.Adapter<admin_ques_adapter.problemListViewHolder>() {

    class problemListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val shortdisc:TextView=itemView.findViewById(R.id.short_disc)
        val fulldisc:TextView=itemView.findViewById(R.id.full_disc)
        val skill:TextView=itemView.findViewById(R.id.skill)
        val reward:TextView=itemView.findViewById(R.id.reward)
        val lastdate:TextView=itemView.findViewById(R.id.lastdate)
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
    }

}



//interface problemItemClicked{
//    fun onItemClicked(item:String)
//}