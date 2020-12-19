package com.example.doe.domain

interface AuthUserLocalDataSource {
    fun save(authUserSession: AuthUserSession)
    fun hasUserSession(callback: (Boolean) -> Unit)
    fun getToken(): String?
}