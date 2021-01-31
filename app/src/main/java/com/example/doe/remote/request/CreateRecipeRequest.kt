package com.example.doe.remote.request

import com.google.gson.annotations.SerializedName

data class CreateRecipeRequest(
    @SerializedName("description") val description: String,
    @SerializedName("prepareMethod") val prepareMethod: String,
    @SerializedName("recipeYield") val recipeYield: String,
    @SerializedName("product") val product: String
)