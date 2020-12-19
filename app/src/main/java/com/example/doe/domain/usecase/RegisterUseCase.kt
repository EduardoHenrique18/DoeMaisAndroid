package com.example.doe.domain.usecase

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.doe.domain.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter

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

        var formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        var date = LocalDate.parse(input.dateOfBirth, formatter)

        registerService.register(
            input.email,
            input.userPassword,
            input.name,
            date.toString(),
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
    val name: String,
    val dateOfBirth: String
)

enum class RegisterStatus {
    EMPTY_USER_DETAIL_ERROR,
    REGISTERED
}

data class RegisterUserResponse(
    val registered: Boolean,
    val status: RegisterStatus
)