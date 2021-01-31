package com.example.doe.remote.request

import com.google.gson.annotations.SerializedName

data class CreateUserRequest(
    @SerializedName("name") val userName: String,
    @SerializedName("password") val userPassword: String,
    @SerializedName("email") val email: String
)