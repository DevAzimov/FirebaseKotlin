package com.magicapp.firebasekotlin.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.magicapp.firebasekotlin.R
import com.magicapp.firebasekotlin.managers.AuthHandler
import com.magicapp.firebasekotlin.managers.AuthManager
import com.magicapp.firebasekotlin.utils.Extensions.toast
import java.lang.Exception

class SignUpActivity : BaseActivity() {
    val TAG = SignUpActivity::class.java.toString()
    lateinit var et_fullname: EditText
    lateinit var et_password: EditText
    lateinit var et_email: EditText
    lateinit var et_cpassword: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        initViews()
    }

    fun initViews(){
        et_fullname = findViewById(R.id.et_fullname)
        et_email = findViewById(R.id.et_email)
        et_password = findViewById(R.id.et_password)
        et_cpassword = findViewById(R.id.et_cpassword)

        val b_signup = findViewById<Button>(R.id.b_signup)
        b_signup.setOnClickListener {
            val email = et_email.text.toString().trim()
            val password = et_password.text.toString().trim()
            firebaseSignUp(email,password)
        }
        val tv_signin = findViewById<TextView>(R.id.tv_signin)
        tv_signin.setOnClickListener { callSignInActivity(this) }
    }

    fun firebaseSignUp(email:String,password:String){
        AuthManager.signUp(email,password,object : AuthHandler {
            override fun onSuccess() {
                dismissLoading()
                toast("Signed up successfully")
                callMainActivity(context)
            }

            override fun onError(exception: Exception?) {
                dismissLoading()
                toast("Sign up failed")
            }

        })
    }
}