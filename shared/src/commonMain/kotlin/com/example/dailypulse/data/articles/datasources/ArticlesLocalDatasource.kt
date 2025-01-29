package com.example.dailypulse.data.articles.datasources

import com.example.dailypulse.data.articles.dto.ArticleDTO
import com.example.dailypulse.db.DailyPulseDatabase

class ArticlesLocalDatasource(private val database: DailyPulseDatabase) {

    fun getAllArticles(): List<ArticleDTO> =
        database.dailyPulseDatabaseQueries.selectAllArticles(::mapToArticleRow).executeAsList()

    fun insertArticles(article: List<ArticleDTO>) {
        database.dailyPulseDatabaseQueries.transaction {
            article.forEach { articleRow -> insertArticle(articleRow) }
        }
    }

    fun clearArticles() = database.dailyPulseDatabaseQueries.removeAllArticles()

    private fun insertArticle(articleRow: ArticleDTO) {
        database.dailyPulseDatabaseQueries.insertArticle(
            articleRow.title,
            articleRow.desc,
            articleRow.date,
            articleRow.imageUrl
        )
    }

    private fun mapToArticleRow(
        title: String,
        desc: String?,
        date: String,
        url: String?,
    ) : ArticleDTO = ArticleDTO(title, desc, date, url)
}