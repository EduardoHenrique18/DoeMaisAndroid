package com.example.doe.remote.response

import com.google.gson.annotations.SerializedName

data class CreatePointDetailResponse(
    @SerializedName("_id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("latitude") val latitude: String,
    @SerializedName("longitude") val longitude: String,
    @SerializedName("image") val image: String,
    @SerializedName("disable") val disable: String
)