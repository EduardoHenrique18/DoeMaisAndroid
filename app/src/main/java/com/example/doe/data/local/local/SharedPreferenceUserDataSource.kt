package com.example.doe.data.local.local

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.example.doe.domain.AuthUserLocalDataSource
import com.example.doe.domain.AuthUserSession

class SharedPreferenceUserDataSource(
    context: Context
) : AuthUserLocalDataSource {
    private val sharedPreference: SharedPreferences by lazy {
        context.getSharedPreferences(
            "user-session",
            Context.MODE_PRIVATE
        )
    }

    override fun save(authUserSession: AuthUserSession) {
        sharedPreference.edit {
            putString("userId", authUserSession.userId)
            putString("userToken", authUserSession.token)
            apply()
        }
    }

    override fun hasUserSession(callback: (Boolean) -> Unit) {
        val token = sharedPreference.getString("userToken", "")
        callback(token != null)
    }

    override fun getToken(): String? {
        return sharedPreference.getString("userToken", "")
    }

}