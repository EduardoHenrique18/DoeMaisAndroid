package com.example.doe.remote.response

import com.google.gson.annotations.SerializedName

data class UserSessionResponse(
    @SerializedName("userSearched") val user: UserDetailResponse,
    @SerializedName("token") val token: String
)