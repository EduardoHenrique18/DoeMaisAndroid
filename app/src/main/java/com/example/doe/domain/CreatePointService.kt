package com.example.doe.domain

import com.example.doe.remote.response.CreatePointDetailResponse

interface CreatePointService {
    fun createPoint(name: String, description: String, latitude: String, longitude: String, disable: String, image: String, token: String, callback: (CreatePointDetailResponse?) -> Unit)
}