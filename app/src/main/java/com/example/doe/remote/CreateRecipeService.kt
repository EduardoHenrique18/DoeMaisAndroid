package com.example.doe.remote

import com.example.doe.domain.CreateRecipeService
import com.example.doe.remote.request.CreateRecipeRequest
import com.example.doe.remote.response.BaseResponse
import com.example.doe.remote.response.CreateRecipeDetailResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateRecipeService (
    private val restApi: RestApi
) : CreateRecipeService {

    override fun createRecipe(
        description: String,
        prepareMethod: String,
        product: String,
        recipeYield: String,
        token: String,
        callback: (CreateRecipeDetailResponse?) -> Unit
    ) {
        restApi.createRecipe(
            token,
            CreateRecipeRequest(
                description,
                prepareMethod,
                product,
                recipeYield
            )
        )
        .enqueue(object : Callback<BaseResponse<CreateRecipeDetailResponse>> {
            override fun onResponse(
                call: Call<BaseResponse<CreateRecipeDetailResponse>>,
                response: Response<BaseResponse<CreateRecipeDetailResponse>>
            ) {
                if (response.code() == 200) {
                    callback(response.body()?.data)
                } else {
                    callback(null)
                }
            }

            override fun onFailure(
                call: Call<BaseResponse<CreateRecipeDetailResponse>>, t: Throwable) {
                callback(null)
                t.printStackTrace()
            }
        })
    }
}