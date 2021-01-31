package com.example.doe.ui.createRecipe

import com.example.doe.data.local.local.SharedPreferenceUserDataSource
import com.example.doe.domain.UseCase
import com.example.doe.domain.usecase.*
import com.example.doe.ui.createPoint.CreateRecipeContract


class CreateRecipePresenter(
    private val view: CreateRecipeContract.CreateRecipeView,
    private val CreateRecipeUsecase: UseCase<RecipeDetail, CreateRecipeResponse>,
    private val connectivityUseCase: UseCase<Unit, Boolean>,
    private val userDataSource: SharedPreferenceUserDataSource
) : CreateRecipeContract.CreateRecipePresenter {

    override fun onRegisterRecipeClicked() {
        connectivityUseCase.run(Unit, callback = { isConnected ->
            if (isConnected) {
                var token = userDataSource.getToken()
                token = "Bearer $token"
                if (token != null) {
                    createRecipe(token)
                }
            } else {
                view.showNoInternet()
            }
        })
    }

    private fun createRecipe(token: String) {
        val description = view.getDescription()
        val prepareMethod = view.getPrepareMethod()
        val product = view.getProduct()
        val recipeYield = view.getYield()

        CreateRecipeUsecase.run(
            RecipeDetail(
                description,
                prepareMethod,
                product,
                recipeYield,
                token
            ),
            callback = { response ->
                if (response.isRecipeCreated) {
                    view.recipeCreated()
                } else {
                    showError(response.status)
                }
            })
    }

    private fun showError(status: CreateRecipeStatus) {
        when (status) {
            CreateRecipeStatus.EMPTY_RECIPE_DETAIL_ERROR -> view.showEmptyInfoError()
        }
    }
}