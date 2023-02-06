package com.example.skillhack.Admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.skillhack.R

class AdminLoginPage : AppCompatActivity() {
    private lateinit var mobno: EditText
    private lateinit var edtotp: EditText
    private lateinit var getverifybtn: Button
    private lateinit var getotp: Button
    private lateinit var adminPhone:String
    private lateinit var adminpin: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_login_page)
        adminPhone="8800226633"
        adminpin="6633"
        mobno=findViewById(R.id.admin_phone)
        edtotp=findViewById(R.id.input_pin)
        getotp=findViewById(R.id.btnAdmOtp)
        getverifybtn=findViewById(R.id.adminVerify)
        getotp.setOnClickListener(View.OnClickListener {
            if (mobno.text.trim().toString().isEmpty() || mobno.text.trim().toString().length!=10 ){
                Toast.makeText(this, "Please enter a valid phone number.", Toast.LENGTH_LONG).show()
            }
            else if( mobno.text.trim().toString()!=adminPhone){
                Toast.makeText(this, "Please enter a correct admin phone number.", Toast.LENGTH_LONG).show()
            }
            else{
                val phone = "+91" + mobno.text.trim().toString()
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
            if (edtotp.text.trim().toString().isEmpty() ){
                Toast.makeText(this, "Please enter otp.", Toast.LENGTH_LONG).show()
            }
            else if(edtotp.text.trim().toString()!=adminpin){
                Toast.makeText(this, "Please enter valid otp.", Toast.LENGTH_LONG).show()

            }
            else{
                val i = Intent(this, admin_problem_update::class.java)
                startActivity(i)

                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                finish()

            }
        })
    }
}