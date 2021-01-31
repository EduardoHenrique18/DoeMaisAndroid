package com.example.doe.data.local.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.doe.remote.response.CreateRecipeDetailResponse

@Dao
interface RecipeDao {
    @Query("SELECT * FROM CreateRecipeDetailResponse")
    fun getAll(): LiveData<List<CreateRecipeDetailResponse>>

    @Insert
    fun insertAll(recipes: List<CreateRecipeDetailResponse>)

    @Insert
    fun insertOne(recipe: CreateRecipeDetailResponse)

    @Delete
    fun delete(recipe: CreateRecipeDetailResponse)

    @Query("UPDATE CreateRecipeDetailResponse SET description = :description, prepareMethod = :prepareMethod, product = :product, recipeYield = :recipeYield WHERE recipeId = :recipeId")
    fun editRecipe(description: String, prepareMethod: String, recipeYield: String, product: String, recipeId: String)
}