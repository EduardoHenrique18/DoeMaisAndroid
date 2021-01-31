package com.example.doe.ui.home

import androidx.lifecycle.LiveData
import com.example.doe.remote.response.CreateRecipeDetailResponse
import com.example.doe.remote.response.UserDetailResponse

class HomeContract {
    interface HomeView {
        fun showRecipeList(recipes: List<CreateRecipeDetailResponse>)
        fun showRecipeDeleted()
        fun openRegisterRecipe()
        fun logout()
    }

    interface HomePresenter {
        fun onClickedItem(recipeInfo: CreateRecipeDetailResponse)
        fun onClickedDeleteItemOption(recipeInfo: CreateRecipeDetailResponse)
        fun onClickedEditItemOption(recipeInfo: CreateRecipeDetailResponse)
        fun searchRecipe()
        fun onCreateRecipeClicked()
        fun onLogoutClicked()
        fun recipeListObserve(): LiveData<List<CreateRecipeDetailResponse>>?
    }
}