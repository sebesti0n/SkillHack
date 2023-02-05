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
   private lateinit var progressbar:ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_login)
        enternumber=findViewById(R.id.input_Mob_Num)
        getotpbtn=findViewById(R.id.btnGetOtp)
        progressbar=findViewById(R.id.PB_otp_sending)
        progressbar.visibility=View.INVISIBLE
        auth=FirebaseAuth.getInstance()


        getotpbtn?.setOnClickListener {

            Log.d("TAG","get otp btn starts")
            mobilenumber=enternumber?.text?.trim().toString()
            if(!mobilenumber.isEmpty()){
                if((mobilenumber).length==10){
                        mobilenumber = "+91$mobilenumber"
                    progressbar.visibility=View.VISIBLE
                    Log.d("TAG","OTP generating")
                    val options = PhoneAuthOptions.newBuilder(auth)
                        .setPhoneNumber(mobilenumber)
                        .setTimeout(60L, TimeUnit.SECONDS)
                        .setActivity(this)
                        .setCallbacks(callbacks)
                        .build()
                    PhoneAuthProvider.verifyPhoneNumber(options)
                    Log.d("TAG","OTP generated")
//
//                    finish()
                }
                else{
                    Toast.makeText(this,"Please enter correct number",Toast.LENGTH_LONG).show()
                }
            }
            else{
                Toast.makeText(this,"Enter Mobile number",Toast.LENGTH_SHORT).show()
            }

        }
    }


   private val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
            signInWithPhoneAuthCredential(credential)
            Log.d("TAG","CallBank function 1")
        }

        override fun onVerificationFailed(e: FirebaseException) {
            Log.d("TAG","CallBank function 2")
          //  Log.w(TAG, "onVerificationFailed", e)

            if (e is FirebaseAuthInvalidCredentialsException) {
                Log.d("TAG","onVerificationFailed :${e.toString()}")

            } else if (e is FirebaseTooManyRequestsException) {
                Log.d("TAG","onVerificationFailed :${e.toString()}")
            }
          //  progressbar.visibility=View.VISIBLE
        }

        override fun onCodeSent(
            verificationId: String,
            token: PhoneAuthProvider.ForceResendingToken
        ) {Log.d("TAG","CallBank function 3  Shift to verification page")
                       val intent=Intent(this@Login,checker::class.java)
//                        intent.putExtra("OTP",verificationId)
//                        intent.putExtra("resendtoken",token)
//                        intent.putExtra("mobile_Number",mobilenumber)
            Log.d("TAG","CallBank function 3  Shifting to verification page ${token.toString()}")
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                        startActivity(intent)

//                    finish()
            progressbar.visibility=View.INVISIBLE
        }
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        Log.d("TAG","CallBank function 1 signInWithPhoneAuthCredential")
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this,"Authenticated Successfully",Toast.LENGTH_SHORT).show()

                    sendToProfile()

                } else {

                    Log.d("TAG", "signInWithPhoneAuthCredential: ${task.exception.toString()}")
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