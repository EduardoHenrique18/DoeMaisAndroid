package com.example.doe.ui.createPoint

class CreateRecipeContract {
    interface CreateRecipeView {
        fun getDescription(): String
        fun getPrepareMethod(): String
        fun getYield() : String
        fun getProduct() : String
        fun showEmptyInfoError()
        fun showNoInternet()
        fun recipeCreated()
    }

    interface CreateRecipePresenter {
        fun onRegisterRecipeClicked()
    }
}