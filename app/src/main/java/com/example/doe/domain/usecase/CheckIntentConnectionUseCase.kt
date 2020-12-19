package com.example.doe.domain.usecase

import com.example.doe.domain.IConnectivityController
import com.example.doe.domain.IWifiController
import com.example.doe.domain.UseCase

class CheckIntentConnectionUseCase(
    private val wifiController: IWifiController,
    private val connectivityController: IConnectivityController
) : UseCase<Unit, Boolean> {
    override fun run(input: Unit, callback: (Boolean) -> Unit) {
        if (!wifiController.isWifiOptionEnabled()) {
            callback(false)
        } else if (!connectivityController.hasInternetConnected()) {
            callback(false)
        } else {
            callback(true)
        }
    }
}