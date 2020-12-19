package com.example.doe.ui.createPoint

class CreatePointContract {
    interface CreatePointView {
        fun getName(): String
        fun getDescription(): String
        fun getImage() : String
        fun getLatitude(): Double
        fun getLongitude() : Double
        fun showEmptyInfoError()
        fun showNoInternet()
        fun pointCreated()
    }

    interface CreatePointPresenter {
        fun onRegisterpointClicked()
    }
}