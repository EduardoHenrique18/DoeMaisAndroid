package com.example.doe.remote

import com.example.doe.domain.SearchPointService
import com.example.doe.remote.response.BaseResponse
import com.example.doe.remote.response.CreatePointDetailResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchPointService (
    private val restApi: RestApi
) : SearchPointService {

    override fun searchPoint(
        token: String,
        callback: (List<CreatePointDetailResponse>?) -> Unit
    ) {
        restApi.searchPoint(
            token
        )
        .enqueue(object : Callback<BaseResponse<List<CreatePointDetailResponse>>> {
            override fun onResponse(
                call: Call<BaseResponse<List<CreatePointDetailResponse>>>,
                response: Response<BaseResponse<List<CreatePointDetailResponse>>>
            ) {
                if (response.code() == 200) {
                    callback(response.body()?.data)
                } else {
                    callback(null)
                }
            }

            override fun onFailure(
                call: Call<BaseResponse<List<CreatePointDetailResponse>>>, t: Throwable) {
                callback(null)
                t.printStackTrace()
            }
        })
    }

}