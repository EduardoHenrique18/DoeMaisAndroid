package com.example.doe.ui.login

import com.example.doe.domain.UseCase
import com.example.doe.domain.usecase.LoginUserResponse
import com.example.doe.domain.usecase.LoginUserStatus
import com.example.doe.domain.usecase.UserDetail

class LoginPresenter(
    private val view: LoginContract.LoginView,
    private val loginUseCase: UseCase<UserDetail, LoginUserResponse>,
    private val connectivityUseCase: UseCase<Unit, Boolean>
) : LoginContract.LoginPresenter {
    override fun onLoginClicked() {
        connectivityUseCase.run(Unit, callback = { isConnected ->
            if (isConnected) {
                login()
            } else {
                view.showNoInternet()
            }
        })
    }

    override fun onRegisterClicked() {
        view.openRegisterScreen()
    }

    private fun login() {
        val email = view.getEmail()
        val userPassword = view.getUserPassword()

        loginUseCase.run(
            UserDetail(
                email,
                userPassword
            ),
            callback = { response ->
                if (response.isAuthenticatedUser) {
                    view.openHomeScreen()
                } else {
                    showError(response.status)
                }
            })
    }

    private fun showError(status: LoginUserStatus) {
        when (status) {
            LoginUserStatus.EMPTY_USER_DETAIL_ERROR -> view.showEmptyInfoError()
            LoginUserStatus.UNAUTHENTICATED_USER_ERROR -> view.showUnauthUserError()
        }
    }
}