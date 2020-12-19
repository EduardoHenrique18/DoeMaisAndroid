package com.example.doe.remote.request

import com.google.gson.annotations.SerializedName

data class UserLoginRequest(
    @SerializedName("email") val email: String,
    @SerializedName("password") val userPassword: String
)