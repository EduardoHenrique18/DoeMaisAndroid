package com.example.doe.domain.usecase

import com.example.doe.domain.AuthUserLocalDataSource
import com.example.doe.domain.CreatePointService
import com.example.doe.domain.UseCase

class CreatePointUseCase(
    private val createPointService: CreatePointService,
    private val authUserLocalDataSource: AuthUserLocalDataSource
) : UseCase<PointDetail, CreatePointResponse> {
    override fun run(input: PointDetail, callback: (CreatePointResponse) -> Unit) {
        if (input.name.isEmpty() || input.description.isEmpty() || input.image.isEmpty()) {
            callback(
                CreatePointResponse(
                    false,
                    CreatePointStatus.EMPTY_POINT_DETAIL_ERROR
                )
            )
            return
        }

        createPointService.createPoint(
            input.name,
            input.description,
            input.latitude,
            input.longitude,
            "true",
            input.image,
            input.token,
            callback = { point ->
                if (point != null) {
                    callback(
                        CreatePointResponse(
                            true,
                            CreatePointStatus.POINT_CREATED
                        )
                    )
                }
            }
        )
    }
}

data class PointDetail(
    val name: String,
    val description: String,
    val latitude: String,
    val longitude: String,
    val token: String,
    val image: String
)

enum class CreatePointStatus {
    POINT_CREATED,
    EMPTY_POINT_DETAIL_ERROR
}

data class CreatePointResponse(
    val isPointCreated: Boolean,
    val status: CreatePointStatus
)