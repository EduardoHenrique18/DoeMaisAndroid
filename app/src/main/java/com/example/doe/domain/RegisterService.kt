package com.example.doe.domain

interface RegisterService {
    fun register(email: String, userPassword: String, name: String, dateOfBirth: String, callback: (AuthUserSession?) -> Unit)
}