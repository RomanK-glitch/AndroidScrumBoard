package com.roman.androidscrumboard.ui.logIn

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.lifecycle.Observer
import com.roman.androidscrumboard.MainActivity
import com.roman.androidscrumboard.R
import com.roman.androidscrumboard.models.User
import com.roman.androidscrumboard.ui.register.RegisterActivity

class LogInActivity : AppCompatActivity() {

    private val logInViewModel = LogInViewModel()

    //declare views
    private lateinit var userNameET: EditText
    private lateinit var passwordET: EditText
    private lateinit var btnLogIn: Button
    private lateinit var btnSignUp: Button
    private lateinit var progressBar: ProgressBar
    private lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        //initialize views
        userNameET = findViewById(R.id.log_in_user_name_edit_text)
        passwordET = findViewById(R.id.log_in_password_edit_text)
        btnLogIn = findViewById(R.id.log_in_button)
        btnSignUp = findViewById(R.id.log_in_to_register_button)
        progressBar = findViewById(R.id.log_in_progress_bar)
        imageView = findViewById(R.id.log_in_image)

        //to register activity
        btnSignUp.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        btnLogIn.setOnClickListener {
            logInViewModel.logIn(userNameET.text.toString(), passwordET.text.toString())
        }

        logInViewModel.state.observe(this,
            Observer<LogInState> {state ->
                when(state) {
                    is LogInState.DefaultState -> {
                        defaultStateFun()
                    }
                    is LogInState.SendingState -> {
                        sendingStateFun()
                    }
                    is LogInState.SuccessState<*> -> {
                        successStateFun(state.user)
                    }
                    is LogInState.ErrorState<*> -> {
                        errorStateFun(state.message)
                    }
                }
        })
    }

    private fun defaultStateFun() {

    }

    private fun sendingStateFun() {
        imageView.visibility = View.INVISIBLE
        progressBar.visibility = View.VISIBLE
        userNameET.isEnabled = false
        passwordET.isEnabled = false
        btnSignUp.isEnabled = false
        btnLogIn.isEnabled = false
    }

    private fun successStateFun(user: Any?) {
        if (user is User) {
            //Toast.makeText(this, user.userName, Toast.LENGTH_LONG).show()
            //send user
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun errorStateFun(message: Any?) {
        if (message is String) {
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        }
        imageView.visibility = View.VISIBLE
        progressBar.visibility = View.INVISIBLE
        userNameET.isEnabled = true
        passwordET.isEnabled = true
        btnSignUp.isEnabled = true
        btnLogIn.isEnabled = true
    }
}
