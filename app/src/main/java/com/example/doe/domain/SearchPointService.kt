package com.example.doe.domain

import com.example.doe.remote.response.CreatePointDetailResponse

interface SearchPointService {
    fun searchPoint(token: String, callback: (List<CreatePointDetailResponse>?) -> Unit)
}