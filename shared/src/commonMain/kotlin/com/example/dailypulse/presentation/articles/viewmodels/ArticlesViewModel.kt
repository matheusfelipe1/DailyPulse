package com.example.dailypulse.presentation.articles.viewmodels

import com.example.dailypulse.BaseViewModel
import com.example.dailypulse.domain.articles.usecases.GetArticlesUseCase
import com.example.dailypulse.presentation.articles.state.ArticlesState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

typealias ArticlesStateCallback = (ArticlesState) -> Unit

class ArticlesViewModel(private val getArticlesUseCase: GetArticlesUseCase): BaseViewModel() {

    private val _articlesState: MutableStateFlow<ArticlesState> =
        MutableStateFlow(ArticlesState(loading = true))

    val articlesState: StateFlow<ArticlesState> get() = _articlesState

    private var job: Job? = null


    init {
        getArticles()
    }

    fun startObserving(callback: ArticlesStateCallback) {
        job = CoroutineScope(Dispatchers.IO).launch {
            _articlesState.collect { state ->
                callback(state)
            }
        }
    }

    fun stopObserving() {
        job?.cancel()
    }

    fun getArticles(forceFetch: Boolean = false) {
        scope.launch {
            if (!forceFetch) {
                _articlesState.emit(ArticlesState(loading = true))
            } else {
                _articlesState.emit(
                    ArticlesState(
                        isRefreshing = true,
                        articles = _articlesState.value.articles
                    )
                )
            }

            val articlesResult = getArticlesUseCase.getArticles(forceFetch)

            articlesResult.processResult(
                onSuccess = { data ->
                    _articlesState.emit(ArticlesState(articles = data))
                },
                onFailure = { error ->
                    val messageError = error.message ?: error.cause?.message ?: error.toString()
                    _articlesState.emit(ArticlesState(errorMessage = messageError))
                }
            )
        }
    }
}