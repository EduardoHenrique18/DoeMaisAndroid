package com.example.doe.domain

interface RecipeListLocalDataSource {
    fun save(authUserSession: AuthUserSession)
    fun hasUserSession(callback: (Boolean) -> Unit)
    fun getToken(): String?
}