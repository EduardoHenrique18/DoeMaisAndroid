package com.example.doe.domain.usecase

import com.example.doe.domain.DeleteRecipeService
import com.example.doe.domain.UseCase
import com.example.doe.remote.response.CreateRecipeDetailResponse
import com.example.doe.remote.response.DeleteRecipeDetailResponse
import com.example.doe.ui.home.RecipeInfo

class DeleteRecipeUseCase(
    private val deleteRecipeService: DeleteRecipeService
) : UseCase<DeleteRecipeDetail, DeleteRecipeDetailResponse> {
    override fun run(input: DeleteRecipeDetail, callback: (DeleteRecipeDetailResponse) -> Unit) {
        deleteRecipeService.deleteRecipe(
            input.token,
            input.recipeId,
            callback = { recipe ->
                if (recipe != null) {
                    callback(
                        recipe
                    )
                }
            }
        )
    }

}

data class DeleteRecipeDetail(
    val token: String,
    val recipeId: String
)