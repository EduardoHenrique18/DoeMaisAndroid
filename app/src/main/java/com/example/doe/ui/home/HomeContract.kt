package com.example.doe.ui.home

import com.example.doe.remote.response.CreatePointDetailResponse

class HomeContract {
    interface HomeView {
        fun updatePoints(points: List<CreatePointDetailResponse>)
    }

    interface HomePresenter {
        fun searchPoints()
    }
}