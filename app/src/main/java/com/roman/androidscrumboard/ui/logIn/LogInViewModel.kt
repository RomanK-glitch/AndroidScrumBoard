package com.roman.androidscrumboard.ui.logIn

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.roman.androidscrumboard.UserPost
import com.roman.androidscrumboard.extentions.default
import com.roman.androidscrumboard.extentions.set
import com.roman.androidscrumboard.models.User
import com.roman.androidscrumboard.models.UserLocalStore
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LogInViewModel(application: Application): AndroidViewModel(application) {

    private val userLocalStore = UserLocalStore(getApplication())
    var state = MutableLiveData<LogInState>().default(initialValue = LogInState.DefaultState())

    fun loggedIn(){
        if (userLocalStore.isUserLoggedIn()){
            state.set(newValue = LogInState.SuccessState(userLocalStore.getLoggedInUser()))
        }
    }

    fun logIn(userName: String, password: String) {
        if (userLocalStore.isUserLoggedIn()) {
            state.set(newValue = LogInState.SuccessState(userLocalStore.getLoggedInUser()))
            return
        }
        if (!validateUserName(userName)) {
            state.set(newValue = LogInState.ErrorState("User name field is not filled"))
            return
        }
        if (!validatePassword(password)) {
            state.set(newValue = LogInState.ErrorState("Password field is not filled"))
            return
        } else {
            state.set(newValue = LogInState.SendingState())

            val user = User(userName = userName, password = password)

            //request retrofit get user
            val retrofit = Retrofit.Builder()
                .baseUrl("http://mrkoste6.beget.tech/scrum_board/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val userPost: UserPost = retrofit.create(UserPost::class.java)
            val call: Call<User> = userPost.loginUser(user)
            call.enqueue(object: Callback<User>{
                override fun onFailure(call: Call<User>, t: Throwable) {
                    state.set(newValue = LogInState.ErrorState("Wrong name or password"))
                }

                override fun onResponse(call: Call<User>, response: Response<User>) {
                    userLocalStore.storeUserData(response.body()!!)
                    state.set(newValue = LogInState.SuccessState(response.body()))
                }
            })
            return
        }
    }

    private fun validateUserName(userName: String): Boolean {
        return userName.isNotEmpty()
    }

    private fun validatePassword(password: String): Boolean {
        return password.isNotEmpty()
    }
}
