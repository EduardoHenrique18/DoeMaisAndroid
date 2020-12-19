package com.example.doe.device

import com.example.doe.domain.IConnectivityController

class QDeviceConnectivityController : IConnectivityController {
    override fun hasInternetConnected(): Boolean {
        return true
    }
}