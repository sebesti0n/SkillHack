package com.example.skillhack
import android.app.DatePickerDialog
import android.content.Intent
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.google.android.gms.tasks.OnFailureListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore

class Details : AppCompatActivity() {
    private lateinit var uname:EditText
    private lateinit var  udob:EditText
    private lateinit var  ubtn:Button
    private lateinit var auth: FirebaseAuth
    private lateinit var fstore: FirebaseFirestore
    private lateinit var userID:String
    private lateinit var udata:UserDetails
    private lateinit var  cald:ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        uname=findViewById(R.id.enterName)
        udob=findViewById(R.id.dob)
        ubtn=findViewById(R.id.Userbutton)
        cald=findViewById(R.id.calender)
        cald.setOnClickListener{
            datepickered()
        }
        fstore=FirebaseFirestore.getInstance()
        auth=FirebaseAuth.getInstance()


        val mobNo:String=intent.getStringExtra("phone Number").toString()



        ubtn.setOnClickListener{
            val name=uname.text.toString()
            val dob=udob.text.toString()

            val arrayList = ArrayList<String>()
            if (name.isEmpty()&&dob.isEmpty()) {

                Toast.makeText(this, "Please add all data correctly.", Toast.LENGTH_SHORT).show()
            } else {

                addDatatoDatabase(name, dob, mobNo, arrayList, 0)
                val i = Intent(this, Problem_List::class.java)
                startActivity(i)
                Log.d("TAG", "starting Problem list")
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                finish()
            }
        }
    }

    private fun addDatatoDatabase(name: String, dob: String, mobNo: String, arrayList: ArrayList<String>, i: Int) {
        userID= auth.currentUser!!.uid
        udata=UserDetails()
        udata.setTotalReward(i)
        udata.setdob(dob)
        udata.setname(name)
        udata.setmobNum(mobNo)
        udata.setskills(arrayList)
        Log.d("TAG", uname.text.toString())
        Log.d("TAG", udob.text.toString())
        val fRef: DocumentReference =fstore.collection("users").document(userID)

        fRef.set(udata).addOnSuccessListener {

        }.addOnFailureListener(OnFailureListener {
            Log.d("TAG", "On Failure$it")
        })

    }


    private fun datepickered(){
        val calend=java.util.Calendar.getInstance()
        val year=calend.get(Calendar.YEAR)
        val month=calend.get(Calendar.MONTH)
        val day=calend.get(Calendar.DAY_OF_MONTH)
        val dpd=  DatePickerDialog(this,
            { view, selectedyear,  selectedmonth, selecteddayOfMonth->
                val selecteddate="$selecteddayOfMonth/${1+selectedmonth}/$selectedyear"
                udob.setText(selecteddate)
            },
            year,
            month,
            day
        )
        dpd.show()
    }
}