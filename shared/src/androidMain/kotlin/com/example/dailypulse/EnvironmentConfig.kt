package com.example.dailypulse

import android.content.Context


actual object EnvironmentConfig {
    actual var baseUrl: String = ""
    actual var apiKey: String = ""

    fun initialize(baseUrl: String, apiKey: String) {
        this.baseUrl = baseUrl
        this.apiKey = apiKey
    }
}
