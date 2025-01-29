package com.example.dailypulse.domain.articles.usecases

import com.example.dailypulse.domain.articles.entities.ArticleEntity
import com.example.dailypulse.domain.articles.repository.ArticlesRepository
import com.example.dailypulse.utils.CustomResult

class GetArticlesUseCase(private val repository: ArticlesRepository) {
    suspend fun getArticles(forceFetch: Boolean): CustomResult<List<ArticleEntity>, Throwable> {
        return repository.getArticles(forceFetch)
    }


}