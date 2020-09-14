package com.roman.androidscrumboard

import com.roman.androidscrumboard.models.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserPost {
    @POST("log_in.php")
    fun loginUser(@Body user: User): Call<User>

    @POST("register.php")
    fun registerUser(@Body user: User): Call<User>
}