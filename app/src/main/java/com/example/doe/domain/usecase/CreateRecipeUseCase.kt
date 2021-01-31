package com.example.doe.domain.usecase

import com.example.doe.MyApplication
import com.example.doe.domain.AuthUserLocalDataSource
import com.example.doe.domain.CreateRecipeService
import com.example.doe.domain.UseCase

class CreateRecipeUseCase(
    private val createRecipeService: CreateRecipeService,
    private val authUserLocalDataSource: AuthUserLocalDataSource
) : UseCase<RecipeDetail, CreateRecipeResponse> {
    override fun run(input: RecipeDetail, callback: (CreateRecipeResponse) -> Unit) {
        if (input.prepareMethod.isEmpty() || input.description.isEmpty() || input.product.isEmpty() || input.yield.isEmpty()) {
            callback(
                CreateRecipeResponse(
                    false,
                    CreateRecipeStatus.EMPTY_RECIPE_DETAIL_ERROR
                )
            )
            return
        }

        createRecipeService.createRecipe(
            input.description,
            input.prepareMethod,
            input.product,
            input.yield,
            input.token,
            callback = { recipe ->
                if (recipe != null) {
                    callback(
                        CreateRecipeResponse(
                            true,
                            CreateRecipeStatus.RECIPE_CREATED
                        )
                    )
                    MyApplication.recipeDb?.recipeDao()?.insertOne(recipe)
                }
            }
        )
    }
}

data class RecipeDetail(
    val description: String,
    val prepareMethod: String,
    val product:String,
    val yield: String,
    val token: String
)

enum class CreateRecipeStatus {
    RECIPE_CREATED,
    EMPTY_RECIPE_DETAIL_ERROR
}

data class CreateRecipeResponse(
    val isRecipeCreated: Boolean,
    val status: CreateRecipeStatus
)