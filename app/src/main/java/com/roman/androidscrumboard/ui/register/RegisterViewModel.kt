package com.roman.androidscrumboard.ui.register

import androidx.lifecycle.MutableLiveData
import com.roman.androidscrumboard.UserPost
import com.roman.androidscrumboard.extentions.default
import com.roman.androidscrumboard.extentions.set
import com.roman.androidscrumboard.models.User
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class RegisterViewModel {
    val state = MutableLiveData<RegisterState>().default(initialValue = RegisterState.DefaultState())

    fun register(userName: String, eMail: String, password: String, repPassword: String) {
        if (!validateUserName(userName)) {
            state.set(newValue = RegisterState.ErrorState("User name field is not filled"))
            return
        }
        if (!validateEMail(eMail)) {
            state.set(newValue = RegisterState.ErrorState("Invalid E-mail"))
            return
        }
        if (!validatePassword(password)) {
            state.set(newValue = RegisterState.ErrorState("Password field is not filled"))
            return
        }
        if (!validateRepPassword(password, repPassword)) {
            state.set(newValue = RegisterState.ErrorState("Password and confirmation password do not match"))
            return
        }
        else {
            state.set(newValue = RegisterState.LoadingState())

            val user = User(userName = userName, eMail = eMail, password = password)

            val retrofit = Retrofit.Builder()
                .baseUrl("http://mrkoste6.beget.tech/scrum_board/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val userPost: UserPost = retrofit.create(UserPost::class.java)
            val call: Call<User> = userPost.postUser(user)
            call.enqueue(object: Callback<User> {
                override fun onFailure(call: Call<User>, t: Throwable) {
                    state.set(newValue = RegisterState.ErrorState("This name already taken"))
                }

                override fun onResponse(call: Call<User>, response: Response<User>) {
                    state.set(newValue = RegisterState.SuccessState(response.body()))
                }
            })
            return
        }
    }

    private fun validateUserName(userName: String): Boolean {
        return userName.isNotEmpty()
    }

    private fun validateEMail(eMail: String): Boolean {
        return eMail.contains("@") && eMail.contains(".")
    }

    private fun validatePassword(password: String): Boolean {
        return password.isNotEmpty()
    }

    private fun validateRepPassword(password: String, repPassword: String): Boolean {
        return repPassword.isNotEmpty() && repPassword == password
    }
}