package com.example.cartrack

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.cartrack.Database.DatabaseHelper
import com.hbb20.CountryCodePicker

class RegisterActivity : AppCompatActivity() {
    private var etEmail: EditText? = null
    private var etPassword: EditText? = null
    private var etName: EditText? = null
    private var etMobile: EditText? = null
    private var buttonRegister: Button? = null
    private var databaseHelper: DatabaseHelper? = null
    private var email: String? = null
    private var password: String? = null
    private var name: String? = null
    private var mobile: String? = null
    private var ccp: CountryCodePicker? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        etEmail = findViewById(R.id.et_email)
        etPassword = findViewById(R.id.et_password)
        buttonRegister = findViewById(R.id.button_register)
        etMobile = findViewById(R.id.et_mobile)
        etName = findViewById(R.id.et_name)
        ccp = findViewById(R.id.ccp)
        databaseHelper = DatabaseHelper(this)

        ccp!!.registerCarrierNumberEditText(etMobile)

        initClickEvent()
    }

    private fun initClickEvent() {

        buttonRegister!!.setOnClickListener {
            email = etEmail!!.text.toString()
            password = etPassword!!.text.toString()
            name = etName!!.text.toString()
            mobile = etMobile!!.text.toString()


            if(email == "" && password == "" && name == "" && mobile == ""){
                Toast.makeText(applicationContext, "Please Insert Register Information", Toast.LENGTH_LONG).show()
            } else if (email == "" || email == null) {
                Toast.makeText(applicationContext, "Please Insert Email", Toast.LENGTH_LONG).show()
            } else if(password == "" || password == null){
                Toast.makeText(applicationContext, "Please Insert Password", Toast.LENGTH_LONG).show()
            } else if(name == "" || name == null){
                Toast.makeText(applicationContext, "Please Insert Username", Toast.LENGTH_LONG).show()
            } else if(mobile == "" || mobile == null){
                Toast.makeText(applicationContext, "Please Insert Phone Number", Toast.LENGTH_LONG).show()
            } else{
                validatePhoneNumber()
            }
        }
    }

    private fun validatePhoneNumber() {
        ccp!!.setPhoneNumberValidityChangeListener {
            if(it){
               if(ccp!!.isValidFullNumber){
                   mobile = ccp!!.fullNumber
                   val status = databaseHelper!!.addUser(name, email, mobile, password)
                   if (status) {
                       Toast.makeText(applicationContext, "Registration Successfully", Toast.LENGTH_LONG).show()
                       val i = Intent(applicationContext, LoginActivity::class.java)
                       startActivity(i)
                       finish()
                   } else {
                       Toast.makeText(applicationContext, "You are not Registerd!", Toast.LENGTH_LONG).show()
                   }
               }
            }
            else{
                Toast.makeText(applicationContext, "Invalid Phone Number", Toast.LENGTH_LONG).show()
            }
        }
    }
}