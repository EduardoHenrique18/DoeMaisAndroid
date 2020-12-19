package com.example.doe.remote.response

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
    @SerializedName("statusCode") val status: String,
    @SerializedName("data") val data: T,
    @SerializedName("message") val errorMessage: String
)