package com.example.doe.ui.home

import com.example.doe.data.local.local.SharedPreferenceUserDataSource
import com.example.doe.domain.UseCase
import com.example.doe.domain.usecase.*
import com.example.doe.remote.response.CreatePointDetailResponse

class HomePresenter(
    private val view: HomeContract.HomeView,
    private val SearchPointUsecase: UseCase<SearchPointDetail, List<CreatePointDetailResponse>>,
    private val userDataSource: SharedPreferenceUserDataSource
) : HomeContract.HomePresenter {

    override fun searchPoints() {
        var token = userDataSource.getToken()
        token = "Bearer $token"

        SearchPointUsecase.run(
            SearchPointDetail(
                token
            ),
            callback = { response ->
                if (!response.isEmpty()) {
                    view.updatePoints(response)
                }
            })
    }

}