package com.example.dailypulse.domain.articles.repository

import com.example.dailypulse.domain.articles.entities.ArticleEntity
import com.example.dailypulse.utils.CustomResult

interface ArticlesRepository {
    suspend fun getArticles(forceFetch: Boolean): CustomResult<List<ArticleEntity>, Throwable>
}