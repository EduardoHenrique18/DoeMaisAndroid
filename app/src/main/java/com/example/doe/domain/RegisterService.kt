package com.example.doe.domain

interface RegisterService {
    fun register(email: String, userPassword: String, name: String, callback: (AuthUserSession?) -> Unit)
}