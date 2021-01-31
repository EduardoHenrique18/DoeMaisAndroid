package com.example.doe.data.local.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.doe.remote.response.CreateRecipeDetailResponse

@Entity
data class RecipeEntity(
    @PrimaryKey val recipeId: String,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "prepareMethod") val prepareMethod: String?,
    @ColumnInfo(name = "product") val product: String?,
    @ColumnInfo(name = "yield") val yield: String?
) {

    companion object Factory {
        fun createRecipeDetailResponseToEntity(createRecipeDetailResponse: List<CreateRecipeDetailResponse>): List<RecipeEntity> {
            var recipeList: ArrayList<RecipeEntity> = emptyList<RecipeEntity>() as ArrayList<RecipeEntity>

            createRecipeDetailResponse.forEach {
                val recipeEntity = RecipeEntity(it.recipeId, it.description, it.prepareMethod, it.product, it.recipeYield)

                recipeList.add(recipeEntity)
            }

            return recipeList
        }
    }
}