package com.example.doe.remote

import com.example.doe.remote.request.CreateUserRequest
import com.example.doe.remote.request.UserLoginRequest
import com.example.doe.remote.request.CreatePointRequest
import com.example.doe.remote.response.BaseResponse
import com.example.doe.remote.response.CreatePointDetailResponse
import com.example.doe.remote.response.UserSessionResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface RestApi {
    @POST("login")
    fun login(
        @Body userLoginRequest: UserLoginRequest
    ): Call<BaseResponse<UserSessionResponse>>

    @POST("user")
    fun createUser(
        @Body createUserRequest: CreateUserRequest
    ): Call<BaseResponse<UserSessionResponse>>

    @POST("point")
    fun createPoint(
        @Header("Authorization") token: String,
        @Body createPointRequest: CreatePointRequest
    ): Call<BaseResponse<CreatePointDetailResponse>>

    @GET("point")
    fun searchPoint(
        @Header("Authorization") token: String
    ): Call<BaseResponse<List<CreatePointDetailResponse>>>
}