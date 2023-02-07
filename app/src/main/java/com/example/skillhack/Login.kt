package com.example.skillhack


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
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
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import java.util.concurrent.TimeUnit


class Login : AppCompatActivity() {
    private lateinit var auth:FirebaseAuth
    private lateinit var mobno:EditText
    private lateinit var edtotp:EditText
    private lateinit var getverifybtn:Button
    private lateinit var getotp:Button
    private lateinit var verifiedId:String
    private lateinit var adminLogin:TextView
//    private lateinit var fstore:FirebaseFirestore
//    private lateinit var userID:String

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
        val credential = PhoneAuthProvider.getCredential(verifiedId, code)
        signInWithPhoneAuthCredential(credential)
    }

    private fun sendVerificationCode(phone: String) {

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
            super.onCodeSent(verificationId,token)
            verifiedId = verificationId
        }


        override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential) {

            val code = phoneAuthCredential.smsCode


            if (code != null) {
                edtotp.setText(code)
                verifycode(code)
            }
        }


        override fun onVerificationFailed(e: FirebaseException) {
            if (e is FirebaseAuthInvalidCredentialsException) {
                Log.d("TAG","onVerificationFailed :${e.toString()}")

            } else if (e is FirebaseTooManyRequestsException) {
                Log.d("TAG","onVerificationFailed :${e.toString()}")
            }
        }

    }



    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(OnCompleteListener<AuthResult?> { task ->
                if (task.isSuccessful) {
                    val user = Firebase.auth.currentUser
                    if (user != null) {
                        val i = Intent(this, Problem_List::class.java)
                        startActivity(i)
                        finish()
                    } else {val i = Intent(this, Details::class.java)
                        i.putExtra("phone Number", mobno.text.toString())
                        startActivity(i)
                        finish()
                    }
                } else {
                    Toast.makeText(
                        this@Login,
                        task.exception.toString(),
                        Toast.LENGTH_LONG
                    ).show()
                }
            })
    }

}