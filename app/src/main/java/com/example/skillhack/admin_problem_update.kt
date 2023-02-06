package com.example.skillhack
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore


class admin_problem_update : AppCompatActivity() {
    private lateinit var sdQuestion:EditText
    private lateinit var fullQuestion:EditText
    private lateinit var skill:EditText
    private lateinit var reward:EditText
    private lateinit var lastDate:EditText
    private lateinit var sendbtn:Button
    private lateinit var fdata: FirebaseFirestore
    private lateinit var fdataRef: CollectionReference
    private lateinit var data:problemStructure
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_problem_update)
        fullQuestion=findViewById(R.id.description)
        skill=findViewById(R.id.skills_required)
        reward=findViewById(R.id.reward_amount)
        lastDate=findViewById(R.id.review_date)
        sendbtn=findViewById(R.id.submit_button)
        sdQuestion=findViewById(R.id.sd_problem)
        fdata=FirebaseFirestore.getInstance()


        data= problemStructure()
        sendbtn.setOnClickListener(View.OnClickListener {
            val shortQuestion =sdQuestion.text.toString()
            val fullQuestion=fullQuestion.text.toString()
            val skills=skill.text.toString()
            val rwdAmt=reward.text.toString()
            val date=lastDate.text.toString()

            if (shortQuestion.isEmpty()&&fullQuestion.isEmpty()&&skills.isEmpty()&&rwdAmt.isEmpty()&&date.isEmpty()) {

                Toast.makeText(this, "Please add all data correctly.", Toast.LENGTH_SHORT).show();
            } else {
                addDatatoFirebase(shortQuestion,fullQuestion,skills,rwdAmt,date)
            }

        })


    }

    private fun addDatatoFirebase(shortQuestion: String, fullQuestion: String, skills: String, rwdAmt: String, date: String) {


        data.setshortDiscriptionQuestion(shortQuestion)
        data.setfullQuestion(fullQuestion)
        data.setskill(skills)
        data.setrewardAmt(rwdAmt)
        data.setlastDate(date)
        fdataRef=fdata.collection("problemStructure")

        fdataRef.add(data)
            .addOnSuccessListener(OnSuccessListener<DocumentReference?> {

                Toast.makeText(
                    this@admin_problem_update,
                    "Your Problem has been added to Firebase Firestore",
                    Toast.LENGTH_SHORT
                ).show()
            })
            .addOnFailureListener(OnFailureListener { e ->
                Toast.makeText(this@admin_problem_update, "Fail to add course \n$e", Toast.LENGTH_SHORT)
                    .show()
            })

    }
}