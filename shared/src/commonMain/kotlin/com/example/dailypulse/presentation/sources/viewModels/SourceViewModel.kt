package com.example.dailypulse.presentation.sources.viewModels

import com.example.dailypulse.BaseViewModel
import com.example.dailypulse.domain.source.usecases.GetSourcesUseCase
import com.example.dailypulse.presentation.sources.state.SourceState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

typealias SourceCallback = (SourceState) -> Unit

class SourceViewModel(private val getSourcesUseCase: GetSourcesUseCase): BaseViewModel() {

    private val _sourceState: MutableStateFlow<SourceState> =
        MutableStateFlow<SourceState>(SourceState(loading = true))

    val sourceState: StateFlow<SourceState> get() = _sourceState

    private var job: Job? = null

    init {
        getSources()
    }

    fun startObserving(callback: SourceCallback) {
        job = CoroutineScope(Dispatchers.IO).launch {
            _sourceState.collect { state ->
                callback(state)
            }
        }
    }

    fun stopObserving() {
        job?.cancel()
    }

     fun getSources(forceFetch: Boolean = false) {
        scope.launch {
            _sourceState.emit(SourceState(loading = true))

            val sourcesResult = getSourcesUseCase.invoke(forceFetch)
            sourcesResult.processResult(
                onSuccess = { data ->
                    _sourceState.emit(SourceState(sources = data))
                },
                onFailure = { error ->
                    val messageError = error.message ?: error.cause?.message ?: error.toString()
                    _sourceState.emit(SourceState(errorMessage = messageError))
                }
            )

        }
    }
}