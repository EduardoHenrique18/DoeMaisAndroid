package com.example.doe.domain

import com.example.doe.remote.response.CreateRecipeDetailResponse

interface SearchRecipeService {
    fun searchRecipe(token: String, callback: (List<CreateRecipeDetailResponse>?) -> Unit)
}