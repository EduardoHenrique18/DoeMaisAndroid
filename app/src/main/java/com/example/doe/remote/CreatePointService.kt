package com.example.doe.remote

import com.example.doe.domain.AuthUserSession
import com.example.doe.domain.CreatePointService
import com.example.doe.remote.request.CreatePointRequest
import com.example.doe.remote.response.BaseResponse
import com.example.doe.remote.response.CreatePointDetailResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreatePointService (
    private val restApi: RestApi
) : CreatePointService {

    override fun createPoint(
        name: String,
        description: String,
        latitude: String,
        longitude: String,
        disable: String,
        image: String,
        token: String,
        callback: (CreatePointDetailResponse?) -> Unit
    ) {
        restApi.createPoint(
            token,
            CreatePointRequest(
                name,
                description,
                latitude,
                longitude,
                disable,
                image
            )
        )
        .enqueue(object : Callback<BaseResponse<CreatePointDetailResponse>> {
            override fun onResponse(
                call: Call<BaseResponse<CreatePointDetailResponse>>,
                response: Response<BaseResponse<CreatePointDetailResponse>>
            ) {
                if (response.code() == 200) {
                    callback(response.body()?.data)
                } else {
                    callback(null)
                }
            }

            override fun onFailure(
                call: Call<BaseResponse<CreatePointDetailResponse>>, t: Throwable) {
                callback(null)
                t.printStackTrace()
            }
        })
    }
}