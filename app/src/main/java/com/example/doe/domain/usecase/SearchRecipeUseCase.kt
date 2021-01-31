package com.example.doe.domain.usecase

import com.example.doe.domain.SearchRecipeService
import com.example.doe.domain.UseCase
import com.example.doe.remote.response.CreateRecipeDetailResponse
import com.example.doe.ui.home.RecipeInfo

class SearchRecipeUseCase(
    private val searchRecipeService: SearchRecipeService
) : UseCase<SearchRecipeDetail, List<CreateRecipeDetailResponse>> {
    override fun run(input: SearchRecipeDetail, callback: (List<CreateRecipeDetailResponse>) -> Unit) {
        searchRecipeService.searchRecipe(
            input.token,
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

data class SearchRecipeDetail(
    val token: String
)