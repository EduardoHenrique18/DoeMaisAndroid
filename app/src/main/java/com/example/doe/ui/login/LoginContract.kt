package com.example.doe.ui.login

class LoginContract {
    interface LoginView {
        fun getEmail(): String
        fun getUserPassword(): String
        fun openHomeScreen()
        fun openRegisterScreen()
        fun showError()
        fun showEmptyInfoError()
        fun showUnauthUserError()
        fun showNoInternet()
    }

    interface LoginPresenter {
        fun onLoginClicked()
        fun onRegisterClicked()
    }
}