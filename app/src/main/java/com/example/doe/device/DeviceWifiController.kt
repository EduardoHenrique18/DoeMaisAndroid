package com.example.doe.device

import android.content.Context
import android.net.wifi.WifiManager
import com.example.doe.domain.IWifiController

class DeviceWifiController(
    private val context: Context
) : IWifiController {

    override fun isWifiOptionEnabled() =
        (context.applicationContext.getSystemService(
            Context.WIFI_SERVICE) as WifiManager).isWifiEnabled

}