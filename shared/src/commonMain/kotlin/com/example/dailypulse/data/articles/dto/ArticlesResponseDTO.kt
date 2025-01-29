package com.example.dailypulse.data.articles.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArticlesResponseDTO(
    @SerialName("status")
    val status: String,
    @SerialName("totalResults")
    val results: Int,
    @SerialName("articles")
    val articles: List<ArticleDTO>,
)