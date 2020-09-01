package com.roman.androidscrumboard.ui.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import com.roman.androidscrumboard.MainActivity
import com.roman.androidscrumboard.R
import com.roman.androidscrumboard.models.User

class RegisterActivity : AppCompatActivity() {

    private val registerViewModel = RegisterViewModel()

    //declare views
    private lateinit var userNameET: EditText
    private lateinit var eMailET: EditText
    private lateinit var passwordET: EditText
    private lateinit var repPasswordET: EditText
    private lateinit var btnRegister: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        //initialize views
        userNameET = findViewById(R.id.sign_up_user_name_edit_text)
        eMailET= findViewById(R.id.sign_up_e_mail_edit_text)
        passwordET= findViewById(R.id.sign_up_password_edit_text)
        repPasswordET = findViewById(R.id.sign_up_repeat_password_edit_text)
        btnRegister= findViewById(R.id.sign_up_button)

        btnRegister.setOnClickListener {
            registerViewModel.register(userNameET.text.toString(), eMailET.text.toString(), passwordET.text.toString(), repPasswordET.text.toString())
        }

        registerViewModel.state.observe(this,
            Observer<RegisterState> { state ->
            when (state) {
                is RegisterState.DefaultState -> {
                    defaultStateFun()
                }
                is RegisterState.LoadingState -> {
                    loadingStateFun()
                }
                is RegisterState.SuccessState<*> -> {
                    successStateFun(state.user)
                }
                is RegisterState.ErrorState<*> -> {
                    errorStateFun(state.message)
                }
            }
        })
    }

    private fun defaultStateFun() {
        //Toast.makeText(this, "default state", Toast.LENGTH_LONG).show()
    }

    private fun loadingStateFun() {
        //Toast.makeText(this, "loading state", Toast.LENGTH_LONG).show()
    }

    private fun successStateFun(user: Any?) {
        if (user is User){
            Toast.makeText(this, user.eMail, Toast.LENGTH_LONG).show()
//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
//            finish()
        }
    }

    private fun errorStateFun(message: Any?) {
        if (message is String)
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}
