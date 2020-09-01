package com.roman.androidscrumboard.models

import com.google.gson.annotations.SerializedName

data class User (
    @SerializedName("id")
    val id: Int = -1,
    @SerializedName("name")
    val userName: String = "",
    @SerializedName("e_mail")
    val eMail: String = "",
    @SerializedName("image_path")
    val imagePath: String = "",
    val password: String = "") {

}