package com.example.skillhack

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView

import android.widget.Toast
import androidx.annotation.RequiresApi
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*

import java.util.concurrent.TimeUnit

class VerificationOTP : AppCompatActivity() {
    private var verifyotpbtn: Button? =null
    private var inOTP1: EditText? =null
    private var inOTP2: EditText? =null
    private var inOTP3: EditText? =null
    private var inOTP4: EditText?=null
    private var inOTP5: EditText? =null
    private var inOTP6: EditText? =null
    private lateinit var auth:FirebaseAuth
    private lateinit var OTP :String
    private lateinit var resendToken: PhoneAuthProvider.ForceResendingToken

     private lateinit var phonenumber:String
     private lateinit var progressBar:ProgressBar
    private var rsndotp: TextView?=null

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verification_otp)
        Log.d("TAG","verification page start")
        verifyotpbtn=findViewById(R.id.btnverify)
        inOTP1=findViewById(R.id.inputotp1)
        inOTP2=findViewById(R.id.inputotp2)
        inOTP3=findViewById(R.id.inputotp3)
       inOTP4= findViewById(R.id.inputotp4)
        inOTP5=findViewById(R.id.inputotp5)
        inOTP6=findViewById(R.id.inputotp6)
        OTP=intent.getStringExtra("OTP").toString()
        resendToken= intent.getParcelableExtra("resendtoken", PhoneAuthProvider.ForceResendingToken::class.java)!!

        phonenumber=intent.getStringExtra("mobile_number")!!

        auth=FirebaseAuth.getInstance()
//        progressBar=findViewById(R.id.PB_verifying)
        rsndotp=findViewById(R.id.tv_resendotp)
//      generateotp()
        progressBar.visibility = View.INVISIBLE

        Log.d("TAG","verification page start")
        otpmovenxt()
//        resendOTPTvVisibility()
        rsndotp?.setOnClickListener {
            resendVerificationCode()
//            resendOTPTvVisibility()
        }


        verifyotpbtn?.setOnClickListener{
            //// to trim is removed in condition
            val typedOTP = (inOTP1?.text.toString() + inOTP2?.text.toString() + inOTP3?.text.toString()
                        + inOTP4?.text.toString() + inOTP5?.text.toString() + inOTP6?.text.toString())

            if (typedOTP.isNotEmpty()) {
                if (typedOTP.length == 6) {
                    val credential: PhoneAuthCredential = PhoneAuthProvider.getCredential(
                        OTP, typedOTP
                    )
                    progressBar.visibility = View.VISIBLE
                    signInWithPhoneAuthCredential(credential)
                } else {
                    Toast.makeText(this, "Please Enter Correct OTP", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Please Enter OTP", Toast.LENGTH_SHORT).show()
            }


        }

        }
//    private fun resendOTPTvVisibility() {
//        inOTP1?.setText("")
//        inOTP2?.setText("")
//        inOTP3?.setText("")
//        inOTP4?.setText("")
//        inOTP5?.setText("")
//        inOTP6?.setText("")
//        rsndotp?.visibility = View.INVISIBLE
//        rsndotp?.isEnabled = false
//
//        Handler(Looper.myLooper()!!).postDelayed(Runnable {
//            rsndotp?.visibility = View.VISIBLE
//            rsndotp?.isEnabled = true
//        }, 60000)
//    }
    private fun resendVerificationCode() {

        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phonenumber)       // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(this)                 // Activity (for callback binding)
            .setCallbacks(callbacks)
            .setForceResendingToken(resendToken)// OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    private val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
            signInWithPhoneAuthCredential(credential)
        }

        override fun onVerificationFailed(e: FirebaseException) {

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
        ) {


            OTP = verificationId
            resendToken = token

//
        }
    }


    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    progressBar.visibility=View.VISIBLE
                    Toast.makeText(this, "Authenticate Successfully", Toast.LENGTH_SHORT).show()
                    sendToMain()
                } else {
                    Log.d("TAG", "signInWithPhoneAuthCredential: ${task.exception.toString()}")
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {

                    }

                }
                progressBar.visibility = View.VISIBLE
            }
    }

    private fun sendToMain() {
        startActivity(Intent(this, checker::class.java))
    }




    private fun otpmovenxt() {
        inOTP1?.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // to trim is removed in condition
                if (!p0.toString().trim().isEmpty()){
                    inOTP2?.requestFocus()
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })
        inOTP2?.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // to trim is removed in condition
                if (!p0.toString().trim().isEmpty()){
                    inOTP3?.requestFocus()
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })
        inOTP3?.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // to trim is removed in condition
                if (!p0.toString().trim().isEmpty()){
                    inOTP4?.requestFocus()
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })
        inOTP4?.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // to trim is removed in condition
                if (!p0.toString().trim().isEmpty()){
                    inOTP5?.requestFocus()
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })
        inOTP5?.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // to trim is removed in condition
                if (!p0.toString().trim().isEmpty()){
                    inOTP6?.requestFocus()
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })
    }

}