package com.example.doe.remote

import com.example.doe.remote.request.CreateUserRequest
import com.example.doe.remote.request.UserLoginRequest
import com.example.doe.remote.request.CreateRecipeRequest
import com.example.doe.remote.response.BaseResponse
import com.example.doe.remote.response.CreateRecipeDetailResponse
import com.example.doe.remote.response.DeleteRecipeDetailResponse
import com.example.doe.remote.response.UserSessionResponse
import retrofit2.Call
import retrofit2.http.*

interface RestApi {
    @POST("login")
    fun login(
        @Body userLoginRequest: UserLoginRequest
    ): Call<BaseResponse<UserSessionResponse>>

    @POST("user")
    fun createUser(
        @Body createUserRequest: CreateUserRequest
    ): Call<BaseResponse<UserSessionResponse>>

    @POST("recipe")
    fun createRecipe(
        @Header("Authorization") token: String,
        @Body createRecipeRequest: CreateRecipeRequest
    ): Call<BaseResponse<CreateRecipeDetailResponse>>

    @DELETE("recipe")
    fun deleteRecipe(
        @Header("Authorization") token: String,
        @Query("recipeId") recipeId: String
    ): Call<BaseResponse<DeleteRecipeDetailResponse>>

    @GET("recipe")
    fun searchRecipe(
        @Header("Authorization") token: String
    ): Call<BaseResponse<List<CreateRecipeDetailResponse>>>
}