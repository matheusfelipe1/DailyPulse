package com.example.dailypulse

import platform.Foundation.NSProcessInfo

actual object EnvironmentConfig {
    actual var baseUrl = NSProcessInfo.processInfo.environment["BASE_URL"] as String
    actual var apiKey = NSProcessInfo.processInfo.environment["API_KEY"] as String
}