package com.example.doe.domain.usecase

import com.example.doe.domain.AuthUserLocalDataSource
import com.example.doe.domain.AuthUserService
import com.example.doe.domain.UseCase

class LoginUseCase(
    private val authUserService: AuthUserService,
    private val authUserLocalDataSource: AuthUserLocalDataSource
) : UseCase<UserDetail, LoginUserResponse> {
    override fun run(input: UserDetail, callback: (LoginUserResponse) -> Unit) {
        if (input.email.isEmpty() || input.userPassword.isEmpty()) {
            callback(
                LoginUserResponse(
                    false,
                    LoginUserStatus.EMPTY_USER_DETAIL_ERROR
                )
            )
            return
        }

        authUserService.login(
            input.email,
            input.userPassword,
            callback = { authUserSession ->
                if (authUserSession != null) {
                    authUserLocalDataSource.save(authUserSession)
                    callback(
                        LoginUserResponse(
                            true,
                            LoginUserStatus.AUTHENTICATED_USER
                        )
                    )
                } else {
                    callback(
                        LoginUserResponse(
                            false,
                            LoginUserStatus.UNAUTHENTICATED_USER_ERROR
                        )
                    )
                }
            })
    }
}

data class UserDetail(
    val email: String,
    val userPassword: String
)

enum class LoginUserStatus {
    AUTHENTICATED_USER,
    EMPTY_USER_DETAIL_ERROR,
    UNAUTHENTICATED_USER_ERROR
}

data class LoginUserResponse(
    val isAuthenticatedUser: Boolean,
    val status: LoginUserStatus
)