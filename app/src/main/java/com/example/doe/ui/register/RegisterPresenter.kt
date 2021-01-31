package com.example.doe.ui.register

import com.example.doe.domain.UseCase
import com.example.doe.domain.usecase.*

class RegisterPresenter(
    private val view: RegisterContract.RegisterView,
    private val registerUseCase: UseCase<UserDetailRegister, RegisterUserResponse>,
    private val connectivityUseCase: UseCase<Unit, Boolean>
) : RegisterContract.RegisterPresenter {
    override fun onRegisterClicked() {
        connectivityUseCase.run(Unit, callback = { isConnected ->
            if (isConnected) {
                register()
            } else {
                view.showNoInternet()
            }
        })
    }

    private fun register() {
        val email = view.getEmail()
        val userPassword = view.getUserPassword()
        val name = view.getName()

        registerUseCase.run(
            UserDetailRegister(
                email,
                userPassword,
                name
            ),
            callback = { response ->
                if (response.registered) {
                    view.showUserRegistered()
                } else {
                    showError(response.status)
                }
            }
        )
    }

    private fun showError(status: RegisterStatus) {
        when (status) {
            RegisterStatus.EMPTY_USER_DETAIL_ERROR -> view.showEmptyInfoError()
        }
    }
}