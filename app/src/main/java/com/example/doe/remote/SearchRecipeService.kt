package com.example.doe.remote

import com.example.doe.domain.SearchRecipeService
import com.example.doe.remote.response.BaseResponse
import com.example.doe.remote.response.CreateRecipeDetailResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchRecipeService (
    private val restApi: RestApi
) : SearchRecipeService {

    override fun searchRecipe(
        token: String,
        callback: (List<CreateRecipeDetailResponse>?) -> Unit
    ) {
        restApi.searchRecipe(
            token
        )
        .enqueue(object : Callback<BaseResponse<List<CreateRecipeDetailResponse>>> {
            override fun onResponse(
                call: Call<BaseResponse<List<CreateRecipeDetailResponse>>>,
                response: Response<BaseResponse<List<CreateRecipeDetailResponse>>>
            ) {
                if (response.code() == 200) {
                    callback(response.body()?.data)
                } else {
                    callback(null)
                }
            }

            override fun onFailure(
                call: Call<BaseResponse<List<CreateRecipeDetailResponse>>>, t: Throwable) {
                callback(null)
                t.printStackTrace()
            }
        })
    }

}