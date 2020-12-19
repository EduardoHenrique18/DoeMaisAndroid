package com.example.doe.remote.request

import com.google.gson.annotations.SerializedName

data class CreatePointRequest(
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("latitude") val latitude: String,
    @SerializedName("longitude") val longitude: String,
    @SerializedName("disable") val disable: String,
    @SerializedName("image") val image: String
)