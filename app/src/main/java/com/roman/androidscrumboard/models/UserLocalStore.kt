package com.roman.androidscrumboard.models

import android.content.Context
import android.content.SharedPreferences

class UserLocalStore(context: Context) {
    private val spName = "userDetail"
    private val spMode = Context.MODE_PRIVATE
    private val userLocalDataBase: SharedPreferences = context.getSharedPreferences(spName, spMode)

    fun storeUserData(user: User){
        val spEditor = userLocalDataBase.edit()
        spEditor.putInt("userId", user.id)
        spEditor.putString("userName", user.userName)
        spEditor.putString("eMail", user.eMail)
        spEditor.putString("imagePath", user.imagePath)
        spEditor.putString("password", user.password)
        spEditor.putBoolean("loggedIn", true)
        spEditor.apply()
    }

    fun getLoggedInUser(): User {
        val id = userLocalDataBase.getInt("userId", -1)
        val userName = userLocalDataBase.getString("userName", "")
        val eMail = userLocalDataBase.getString("eMail", "")
        val imagePath = userLocalDataBase.getString("imagePath", "")
        val password = userLocalDataBase.getString("password", "")

        return User(id, userName, eMail, imagePath, password)
    }

    fun isUserLoggedIn(): Boolean {
        return userLocalDataBase.getBoolean("loggedIn", false)
    }

    fun clearUserData() {
        val spEditor = userLocalDataBase.edit()
        spEditor.clear()
        spEditor.apply()
    }
}