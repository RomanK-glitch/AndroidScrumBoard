package com.roman.androidscrumboard.ui.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.lifecycle.Observer
import com.google.android.material.navigation.NavigationView
import com.roman.androidscrumboard.MainActivity
import com.roman.androidscrumboard.R
import com.roman.androidscrumboard.models.User

class RegisterActivity : AppCompatActivity() {

    //declare views
    private lateinit var userNameET: EditText
    private lateinit var eMailET: EditText
    private lateinit var passwordET: EditText
    private lateinit var repPasswordET: EditText
    private lateinit var btnRegister: Button
    private lateinit var imageView: ImageView
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val registerViewModel = RegisterViewModel(application)

        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayShowHomeEnabled(true)

        //initialize views
        userNameET = findViewById(R.id.sign_up_user_name_edit_text)
        eMailET= findViewById(R.id.sign_up_e_mail_edit_text)
        passwordET= findViewById(R.id.sign_up_password_edit_text)
        repPasswordET = findViewById(R.id.sign_up_repeat_password_edit_text)
        btnRegister= findViewById(R.id.sign_up_button)
        imageView = findViewById(R.id.register_image)
        progressBar = findViewById(R.id.register_progress_bar)

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
                is RegisterState.SuccessState -> {
                    successStateFun(state.user)
                }
                is RegisterState.ErrorState -> {
                    errorStateFun(state.message)
                }
            }
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun defaultStateFun() {
    }

    private fun loadingStateFun() {
        progressBar.visibility = View.VISIBLE
        imageView.visibility = View.INVISIBLE
        userNameET.isEnabled = false
        eMailET.isEnabled = false
        passwordET.isEnabled = false
        repPasswordET.isEnabled = false
        btnRegister.isEnabled = false
    }

    private fun successStateFun(user: User?) {
        //send user to main activity
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("user", user)
        startActivity(intent)
        finishAffinity()
    }

    private fun errorStateFun(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        imageView.visibility = View.VISIBLE
        progressBar.visibility = View.INVISIBLE
        userNameET.isEnabled = true
        eMailET.isEnabled = true
        passwordET.isEnabled = true
        repPasswordET.isEnabled = true
        btnRegister.isEnabled = true
    }
}
