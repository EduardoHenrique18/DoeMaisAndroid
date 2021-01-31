package com.example.doe.domain

import com.example.doe.remote.response.CreateRecipeDetailResponse
import com.example.doe.remote.response.DeleteRecipeDetailResponse

interface DeleteRecipeService {
    fun deleteRecipe(token: String, recipeId: String, callback: (DeleteRecipeDetailResponse?) -> Unit)
}