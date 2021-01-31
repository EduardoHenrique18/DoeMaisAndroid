package com.example.doe.domain.usecase

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.doe.domain.*

class RegisterUseCase(
    private val registerService: RegisterService
) : UseCase<UserDetailRegister, RegisterUserResponse> {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun run(input: UserDetailRegister, callback: (RegisterUserResponse) -> Unit) {
        if (input.email.isEmpty() || input.userPassword.isEmpty()) {
            callback(
                RegisterUserResponse(
                    false,
                    RegisterStatus.EMPTY_USER_DETAIL_ERROR
                )
            )
            return
        }

        registerService.register(
            input.email,
            input.userPassword,
            input.name,
            callback = { user ->
                if (user != null) {
                    callback(
                        RegisterUserResponse(
                            true,
                            RegisterStatus.REGISTERED
                        )
                    )
                }
            })

    }
}

data class UserDetailRegister(
    val email: String,
    val userPassword: String,
    val name: String
)

enum class RegisterStatus {
    EMPTY_USER_DETAIL_ERROR,
    REGISTERED
}

data class RegisterUserResponse(
    val registered: Boolean,
    val status: RegisterStatus
)