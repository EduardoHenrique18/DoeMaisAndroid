package com.example.doe.domain

interface IConnectivityController {
    abstract fun hasInternetConnected(): Boolean
}