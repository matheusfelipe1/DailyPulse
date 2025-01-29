package com.example.dailypulse.data.articles.repository

import com.example.dailypulse.data.articles.dto.ArticleDTO
import com.example.dailypulse.data.articles.datasources.ArticlesRemoteDatasource
import com.example.dailypulse.data.articles.datasources.ArticlesLocalDatasource
import com.example.dailypulse.data.articles.mappers.articlesMappers
import com.example.dailypulse.domain.articles.entities.ArticleEntity
import com.example.dailypulse.domain.articles.repository.ArticlesRepository
import com.example.dailypulse.utils.BaseRepository
import com.example.dailypulse.utils.CustomResult

class ArticlesRepositoryImpl(
    private val remoteDatasource: ArticlesRemoteDatasource,
    private val localDatasource: ArticlesLocalDatasource
): ArticlesRepository, BaseRepository() {

    override suspend fun getArticles(forceFetch: Boolean): CustomResult<List<ArticleEntity>, Throwable> {
        try {

            suspend fun fetchArticles(): List<ArticleDTO> {
                val fetchedArticles: List<ArticleDTO> = remoteDatasource.fetchArticles()
                if (forceFetch) {
                    localDatasource.clearArticles()
                }
                localDatasource.insertArticles(fetchedArticles)
                return fetchedArticles
            }

            if (forceFetch) {
                val fetchedArticles = fetchArticles()
                return handlerSuccess(articlesMappers(fetchedArticles))
            }


            val dbArticles = localDatasource.getAllArticles()

            if (dbArticles.isEmpty()) {
                val fetchedArticles = fetchArticles()
                return handlerSuccess(articlesMappers(fetchedArticles))
            }

            return handlerSuccess(articlesMappers(dbArticles))
        } catch (e: Throwable) {
            return handlerFailure(e)
        }
    }
}