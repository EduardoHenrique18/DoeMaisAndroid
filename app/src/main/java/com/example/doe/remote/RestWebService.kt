package com.example.doe.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RestWebService {
    var api: RestApi private set
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.1.2:8080")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        api = retrofit.create(RestApi::class.java)
    }
}

