package com.example.dailypulse

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.cio.CIO
import kotlinx.coroutines.CoroutineScope

actual open class BaseViewModel: ViewModel() {
    actual val scope: CoroutineScope = viewModelScope
}

actual fun getEngine(): HttpClientEngineFactory<*> = CIO