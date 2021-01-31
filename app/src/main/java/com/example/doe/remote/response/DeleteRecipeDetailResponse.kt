package com.example.doe.remote.response

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class DeleteRecipeDetailResponse(
    @SerializedName("number") val number: String
)