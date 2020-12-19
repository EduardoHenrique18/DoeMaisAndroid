package com.example.doe.device

import android.content.Context
import android.net.ConnectivityManager
import com.example.doe.domain.IConnectivityController

class DeviceConnectivityController (
    private val context: Context
) : IConnectivityController {
    override fun hasInternetConnected(): Boolean {
        val cm =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm != null
    }
}