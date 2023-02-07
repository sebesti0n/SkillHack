package com.example.skillhack


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.skillhack.Admin.AdminLoginPage

import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnFailureListener
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import org.checkerframework.checker.units.qual.Length
import java.util.concurrent.TimeUnit


class Login : AppCompatActivity(){
    private lateinit var auth:FirebaseAuth
    private lateinit var mobno:EditText
    private lateinit var edtotp:EditText
    private lateinit var getverifybtn:Button
    private lateinit var getotp:Button
    private var verifiedId:String?=null
    private lateinit var adminLogin:TextView
    private lateinit var pb:ProgressBar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
      //  fstore=FirebaseFirestore.getInstance()
        auth=FirebaseAuth.getInstance()
        mobno=findViewById(R.id.input_Mob_Num)
        edtotp=findViewById(R.id.input_otp)
        getverifybtn=findViewById(R.id.btnverified)
        getotp=findViewById(R.id.btnGetOtp)
        adminLogin=findViewById(R.id.tv_adminLogin)
        pb=findViewById(R.id.PbLogin)

        adminLogin.setOnClickListener {
            val i=Intent(this, AdminLoginPage::class.java)

            startActivity(i)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }

        getotp.setOnClickListener(View.OnClickListener {
            if (mobno.text.trim().toString().isEmpty() && mobno.text.trim().toString().length!=10){
                Toast.makeText(this, "Please enter a valid phone number.", Toast.LENGTH_SHORT).show()
            } else{
                val phone = "+91" + mobno.text.trim().toString()
                sendVerificationCode(phone)
                val head=edtotp
                val btn=getverifybtn
                head.alpha=0f
                btn.alpha=0f
                head.animate().setDuration(90).alpha(1f).withEndAction {
                    edtotp.visibility = View.VISIBLE
                    btn.animate().setDuration(90).alpha(1f)
                    getverifybtn.visibility = View.VISIBLE
                }
            }
        })
        getverifybtn.setOnClickListener(View.OnClickListener {
            if (edtotp.text.trim().toString().isEmpty()){
                Toast.makeText(this, "Please enter a valid otp.", Toast.LENGTH_SHORT).show()
            }
            else{

                verifycode(edtotp.text.trim().toString())
            }
        })

    }
    private fun verifycode(code: String) {
        pb.visibility=View.VISIBLE
        val credential = PhoneAuthProvider.getCredential(verifiedId!!,code)
        signInWithPhoneAuthCredential(credential)
    }

    private fun sendVerificationCode(phone: String) {
        pb.visibility=View.VISIBLE

        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phone) // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(this) // Activity (for callback binding)
            .setCallbacks(mCallBack) // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }
    private val mCallBack = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        override fun onCodeSent(
            verificationId: String,
            token: PhoneAuthProvider.ForceResendingToken
        ){
            pb.visibility=View.INVISIBLE
            verifiedId=verificationId
            Log.d("TAG","code$verificationId")
            super.onCodeSent(verificationId,token)


        }


        override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential) {
            pb.visibility=View.INVISIBLE
            val code = phoneAuthCredential.smsCode


            if (code != null) {
                edtotp.setText(code)
                verifycode(code)
            }
        }


        override fun onVerificationFailed(e: FirebaseException) {
            pb.visibility=View.INVISIBLE
            if (e is FirebaseAuthInvalidCredentialsException) {
                Toast.makeText(this@Login,"onVerificationFailed ",Toast.LENGTH_LONG).show()
                Log.d("TAG","onVerificationFailed :${e.toString()}")

            } else if (e is FirebaseTooManyRequestsException) {
                Toast.makeText(this@Login,"Too Many Requests Exception",Toast.LENGTH_LONG).show()
                Log.d("TAG","onVerificationFailed :${e.toString()}")
            }
        }

    }



    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(OnCompleteListener<AuthResult?> { task ->
                if (task.isSuccessful) {

//                  userRef  = FirebaseDatabase.getInstance().getReference("users")
//                    userRef.orderByChild("mobNum").equalTo( mobno.text.toString()).addListenerForSingleValueEvent( ValueEventListener() {
//
//                        if (dataSnapshot.getValue() != null){
//                            //it means user already registered
//                            //Add code to show your prompt
//                            showPrompt();
//                        }else{
//                            //It is new users
//                            //write an entry to your user table
//                            //writeUserEntryToDB();
//                        }
//                    }
                    pb.visibility=View.INVISIBLE
                        val i = Intent(this, Details::class.java)
                        i.putExtra("phone Number", mobno.text.toString())
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                        startActivity(i)
                        finish()
//                    }
                } else {
                    pb.visibility=View.INVISIBLE
                    Toast.makeText(
                        this@Login,
                        task.exception.toString(),
                        Toast.LENGTH_LONG
                    ).show()
                }
            })
    }
}

