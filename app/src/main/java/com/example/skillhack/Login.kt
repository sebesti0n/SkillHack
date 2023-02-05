package com.example.skillhack


import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View

import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import java.util.concurrent.TimeUnit

class Login : AppCompatActivity() {
    private var enternumber:EditText?=null
    private var getotpbtn:Button?=null
    private lateinit var auth:FirebaseAuth
    private lateinit var mobilenumber:String
    var progressbar:ProgressBar=findViewById(R.id.PB_otp_sending)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        enternumber=findViewById(R.id.input_Mob_Num)
        getotpbtn=findViewById(R.id.btnGetOtp)

        progressbar.visibility=View.INVISIBLE
        auth=FirebaseAuth.getInstance()
        getotpbtn?.setOnClickListener {
            mobilenumber=enternumber?.text?.trim().toString()
            if(!mobilenumber.isEmpty()){
                if((mobilenumber).length==10){
                        mobilenumber = "+91$mobilenumber"

                    val options = PhoneAuthOptions.newBuilder(auth)
                        .setPhoneNumber(mobilenumber)
                        .setTimeout(60L, TimeUnit.SECONDS)
                        .setActivity(this)
                        .setCallbacks(callbacks)
                        .build()
                    PhoneAuthProvider.verifyPhoneNumber(options)

//
//                    finish()
                }
                else{
                    Toast.makeText(this,"Please enter correct number",Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(this,"Enter Mobile number",Toast.LENGTH_SHORT).show()
            }

        }
    }


   private val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
            Log.d(TAG, "onVerificationCompleted:$credential")
            signInWithPhoneAuthCredential(credential)
        }

        override fun onVerificationFailed(e: FirebaseException) {

            Log.w(TAG, "onVerificationFailed", e)

            if (e is FirebaseAuthInvalidCredentialsException) {
                Log.d("TAG","onVerificationFailed :${e.toString()}")

            } else if (e is FirebaseTooManyRequestsException) {
                Log.d("TAG","onVerificationFailed :${e.toString()}")
            }
            progressbar.visibility=View.VISIBLE
        }

        override fun onCodeSent(
            verificationId: String,
            token: PhoneAuthProvider.ForceResendingToken
        ) {


                    val intent=Intent(this@Login,VerificationOTP::class.java)
            intent.putExtra("OTP",verificationId)
            intent.putExtra("resendtoken",token)
                    intent.putExtra("mobile_Number",mobilenumber)
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                    startActivity(intent)

                    finish()
            progressbar.visibility=View.INVISIBLE
        }
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {

        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this,"Authenticated Successfully",Toast.LENGTH_LONG).show()

                    sendToProfile()

                } else {

                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {

                    }

                }
            }

    }

   private fun sendToProfile() {
      if(auth.currentUser!=null){
          startActivity(Intent(this@Login,checker::class.java))
      }
    }
}