package com.example.dailypulse.data.articles.mappers

import com.example.dailypulse.data.articles.dto.ArticleDTO
import com.example.dailypulse.domain.articles.entities.ArticleEntity

fun articlesMappers(articles: List<ArticleDTO>): List<ArticleEntity>
        = articles.map { article -> ArticleEntity(
            article.title,
            article.desc ?: "Click to find out",
            article.date,
            article.imageUrl ?: "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTAs_TDUTeHiZQ1tqLJlvItaBOjcmRTeoSbHw&s",
        ) }.toList()