package com.example.doe.data.local.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.doe.remote.response.CreateRecipeDetailResponse

@Database(entities = arrayOf(CreateRecipeDetailResponse::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao
}