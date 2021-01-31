package com.example.doe.domain

import com.example.doe.remote.response.CreateRecipeDetailResponse

interface CreateRecipeService {
    fun createRecipe(description: String,
                     prepareMethod: String,
                     product: String,
                     recipeYield: String,
                     token: String,
                     callback: (CreateRecipeDetailResponse?) -> Unit)
}