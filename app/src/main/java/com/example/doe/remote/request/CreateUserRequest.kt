package com.example.doe.remote.request

import com.google.gson.annotations.SerializedName

data class CreateUserRequest(
    @SerializedName("userName") val userName: String,
    @SerializedName("password") val userPassword: String,
    @SerializedName("dateOfBirth") val dateOfBirth: String,
    @SerializedName("email") val email: String
)