package com.example.doe.domain

interface AuthUserService {
    fun login(userName: String, userPassword: String, callback: (AuthUserSession?) -> Unit)
}