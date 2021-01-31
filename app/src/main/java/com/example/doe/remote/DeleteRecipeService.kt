package com.example.doe.remote

import com.example.doe.domain.DeleteRecipeService
import com.example.doe.remote.response.BaseResponse
import com.example.doe.remote.response.CreateRecipeDetailResponse
import com.example.doe.remote.response.DeleteRecipeDetailResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DeleteRecipeService (
    private val restApi: RestApi
) : DeleteRecipeService {

    override fun deleteRecipe(
        token: String,
        recipeId: String,
        callback: (DeleteRecipeDetailResponse?) -> Unit
    ) {
        restApi.deleteRecipe(
            token,
            recipeId
        )
        .enqueue(object : Callback<BaseResponse<DeleteRecipeDetailResponse>> {
            override fun onResponse(
                call: Call<BaseResponse<DeleteRecipeDetailResponse>>,
                response: Response<BaseResponse<DeleteRecipeDetailResponse>>
            ) {
                if (response.code() == 200) {
                    callback(response.body()?.data)
                } else {
                    callback(null)
                }
            }

            override fun onFailure(
                call: Call<BaseResponse<DeleteRecipeDetailResponse>>, t: Throwable) {
                callback(null)
                t.printStackTrace()
            }
        })
    }

}