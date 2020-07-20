package com.example.cartrack

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.cartrack.Database.DatabaseHelper
import com.hbb20.CountryCodePicker

class LoginActivity : AppCompatActivity() {

    private var etUsername: EditText? = null
    private var etPassword: EditText? = null
    private var etphone: EditText? = null
    private var buttonLogin: Button? = null
    private var databaseHelper: DatabaseHelper? = null
    private var username: String? = null
    private var password: String? = null
    private var phone: String? = null
    private var tvRegister: TextView? = null
    private var ccp: CountryCodePicker? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        etUsername = findViewById(R.id.et_username)
        etPassword = findViewById(R.id.et_password)
        etphone = findViewById(R.id.et_phone)
        buttonLogin = findViewById(R.id.button_login)
        tvRegister = findViewById(R.id.tv_register)
        ccp = findViewById(R.id.ccp)
        databaseHelper = DatabaseHelper(this)

        ccp!!.registerCarrierNumberEditText(etphone)

        initClickEvent()
    }

    private fun initClickEvent() {

        buttonLogin!!.setOnClickListener {
            username = etUsername!!.text.toString()
            password = etPassword!!.text.toString()
            phone = etphone!!.text.toString()

            if (username == "" && password == "" && phone == "") {
                Toast.makeText(applicationContext, "Please Insert Login Information", Toast.LENGTH_LONG).show()
            } else if(username == "" || username == null){
                Toast.makeText(applicationContext, "Please Insert Username", Toast.LENGTH_LONG).show()
            } else if(password == "" || password == null){
                Toast.makeText(applicationContext, "Please Insert Password", Toast.LENGTH_LONG).show()
            } else if(phone == "" || phone == null){
                Toast.makeText(applicationContext, "Please Insert Phone Number", Toast.LENGTH_LONG).show()
            } else if(phone != "" || phone != null){
               validatePhoneNumber()
            }
        }

        tvRegister!!.setOnClickListener {
            val i = Intent(applicationContext, RegisterActivity::class.java)
            startActivity(i)
        }
    }

    private fun validatePhoneNumber() {
        ccp!!.setPhoneNumberValidityChangeListener {
            if(it){
                if(ccp!!.isValidFullNumber){
                    phone = ccp!!.fullNumber
                    val status = databaseHelper!!.getLoginData(username, password, phone).toInt()
                    if (status > 0) {
                        Toast.makeText(applicationContext, "Login Successfully", Toast.LENGTH_LONG).show()
                        val i = Intent(applicationContext, HomePageActivity::class.java)
                        startActivity(i)
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

//    private fun resetEditText() {
//        etUsername!!.text.clear()
//        etPassword!!.text.clear()
//        etphone!!.text.clear()
//    }
}