package com.example.dailypulse.presentation.articles.state

import com.example.dailypulse.domain.articles.entities.ArticleEntity

data class ArticlesState (
    val articles: List<ArticleEntity> = listOf(),
    val loading: Boolean = false,
    val errorMessage: String? = null,
    val isRefreshing: Boolean = false
)