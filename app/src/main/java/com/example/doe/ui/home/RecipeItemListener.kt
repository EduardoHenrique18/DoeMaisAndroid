package com.example.doe.ui.home

import com.example.doe.remote.response.CreateRecipeDetailResponse

interface RecipeItemListener {
    fun onClickItem(recipeInfo: CreateRecipeDetailResponse)
    fun onClickDeleteItem(recipeInfo: CreateRecipeDetailResponse)
    fun onClickEditItem(recipeInfo: CreateRecipeDetailResponse)
}