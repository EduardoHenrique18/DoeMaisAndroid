package com.example.doe.ui.createPoint

import android.content.Context
import android.preference.PreferenceManager
import com.example.doe.data.local.local.SharedPreferenceUserDataSource
import com.example.doe.domain.UseCase
import com.example.doe.domain.usecase.CreatePointResponse
import com.example.doe.domain.usecase.CreatePointStatus
import com.example.doe.domain.usecase.PointDetail
import com.example.doe.domain.usecase.SearchPointDetail


class CreatePointPresenter(
    private val view: CreatePointContract.CreatePointView,
    private val CreatePointUsecase: UseCase<PointDetail, CreatePointResponse>,
    private val connectivityUseCase: UseCase<Unit, Boolean>,
    private val userDataSource: SharedPreferenceUserDataSource
) : CreatePointContract.CreatePointPresenter {

    override fun onRegisterpointClicked() {
        connectivityUseCase.run(Unit, callback = { isConnected ->
            if (isConnected) {
                var token = userDataSource.getToken()
                token = "Bearer $token"
                if (token != null) {
                    createPoint(token)
                }
            } else {
                view.showNoInternet()
            }
        })
    }

    private fun createPoint(token: String) {
        val name = view.getName()
        val description = view.getDescription()
        val image = view.getImage()
        val latitude = view.getLatitude()
        val longitude = view.getLongitude()

        CreatePointUsecase.run(
            PointDetail(
                name,
                description,
                latitude.toString(),
                longitude.toString(),
                token,
                image
            ),
            callback = { response ->
                if (response.isPointCreated) {
                    view.pointCreated()
                } else {
                    showError(response.status)
                }
            })
    }

    private fun showError(status: CreatePointStatus) {
        when (status) {
            CreatePointStatus.EMPTY_POINT_DETAIL_ERROR -> view.showEmptyInfoError()
        }
    }
}