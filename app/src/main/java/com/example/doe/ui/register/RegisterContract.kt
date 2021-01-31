package com.example.doe.ui.register

class RegisterContract {
    interface RegisterView {
        fun getEmail(): String
        fun getUserPassword(): String
        fun getName(): String
        fun showNoInternet()
        fun showUserRegistered()
        fun showEmptyInfoError()
    }

    interface RegisterPresenter {
        fun onRegisterClicked()
    }
}