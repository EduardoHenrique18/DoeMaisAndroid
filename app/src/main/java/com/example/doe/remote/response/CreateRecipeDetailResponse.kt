package com.example.doe.remote.response

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class CreateRecipeDetailResponse(
    @PrimaryKey @SerializedName("recipeId") val recipeId: String,
    @ColumnInfo(name = "description") @SerializedName("description") val description: String,
    @ColumnInfo(name = "prepareMethod") @SerializedName("prepareMethod") val prepareMethod: String,
    @ColumnInfo(name = "recipeYield") @SerializedName("recipeYield") val recipeYield: String,
    @ColumnInfo(name = "product") @SerializedName("product") val product: String
)