package com.example.doe.device

import android.content.Context
import com.example.doe.domain.IConnectivityController

object DeviceConnFactory {
    fun createConnectivityController(context: Context): IConnectivityController {
        return DeviceConnectivityController(context)
    }
}