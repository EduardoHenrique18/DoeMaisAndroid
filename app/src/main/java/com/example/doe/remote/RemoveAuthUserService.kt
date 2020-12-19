package com.example.doe.remote

import com.example.doe.domain.AuthUserService
import com.example.doe.domain.AuthUserSession
import com.example.doe.remote.request.UserLoginRequest
import com.example.doe.remote.response.BaseResponse
import com.example.doe.remote.response.UserSessionResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoveAuthUserService constructor(
    private val restApi: RestApi
) : AuthUserService {
    override fun login(
        userName: String,
        userPassword: String,
        callback: (AuthUserSession?) -> Unit
    ) {
        restApi.login(
            UserLoginRequest(
                userName,
                userPassword
            )
        )
        .enqueue(object : Callback<BaseResponse<UserSessionResponse>> {
            override fun onResponse(
                call: Call<BaseResponse<UserSessionResponse>>,
                response: Response<BaseResponse<UserSessionResponse>>
            ) {
                if (response.code() == 200) {
                    callback(response.body()?.data?.run {
                        AuthUserSession(this.user.id, this.token)
                    })
                } else {
                    callback(null)
                }
            }

            override fun onFailure(
                call: Call<BaseResponse<UserSessionResponse>>, t: Throwable) {
                callback(null)
                t.printStackTrace()
            }
        })
    }
}