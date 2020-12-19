package com.example.doe.domain.usecase

import com.example.doe.domain.SearchPointService
import com.example.doe.domain.UseCase
import com.example.doe.remote.response.CreatePointDetailResponse

class SearchPointUseCase(
    private val searchPointService: SearchPointService
) : UseCase<SearchPointDetail, List<CreatePointDetailResponse>> {
    override fun run(input: SearchPointDetail, callback: (List<CreatePointDetailResponse>) -> Unit) {
        searchPointService.searchPoint(
            input.token,
            callback = { point ->
                if (point != null) {
                    callback(
                        point
                    )
                }
            }
        )
    }
}

data class SearchPointDetail(
    val token: String
)