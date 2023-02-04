package com.example.skillhack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class VerificationOTP : AppCompatActivity() {
    private var verifyotpbtn: Button =findViewById(R.id.btnverify)
    private var inOTP1: EditText =findViewById(R.id.inputotp1)
    private var inOTP2: EditText =findViewById(R.id.inputotp2)
    private var inOTP3: EditText =findViewById(R.id.inputotp3)
    private var inOTP4: EditText =findViewById(R.id.inputotp4)
    private var inOTP5: EditText =findViewById(R.id.inputotp5)
    private var inOTP6: EditText =findViewById(R.id.inputotp6)
    private var rsndotp:TextView=findViewById(R.id.tv_resendotp)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verification_otp)

        verifyotpbtn.setOnClickListener{
            if(!inOTP1.text.toString().isEmpty()&&!inOTP2.text.toString().isEmpty()&&!inOTP3.text.toString().isEmpty()&&!inOTP4.text.toString().isEmpty()&&!inOTP5.text.toString().isEmpty()&&!inOTP6.text.toString().isEmpty())
            {
                Toast.makeText(this,"otp verify", Toast.LENGTH_LONG).show()
            }
            else{
                Toast.makeText(this,"Please enter correct OTP!",Toast.LENGTH_LONG).show()
            }

        }

    }
}