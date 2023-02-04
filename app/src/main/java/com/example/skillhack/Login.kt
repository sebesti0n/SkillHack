package com.example.skillhack

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.autofill.OnClickAction
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class Login : AppCompatActivity() {
    private var enternumber:EditText=findViewById(R.id.input_Mob_Num)
    private var getotpbtn:Button=findViewById(R.id.btnGetOtp)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        getotpbtn.setOnClickListener {
            val mobilenumber=enternumber.text
            if(!mobilenumber.toString().trim().isEmpty()){
                if((mobilenumber.toString().trim()).length==10){
                    val intent=Intent(this,VerificationOTP::class.java)
                    intent.putExtra("mobile_Number",enternumber.text.toString())
                    startActivity(intent)

                   // finish()
                }
                else{
                    Toast.makeText(this,"Please enter correct number",Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(this,"Enter Mobile number",Toast.LENGTH_SHORT).show()
            }

        }
    }
}