package com.example.dailypulse.data.articles.datasources

import com.example.dailypulse.EnvironmentConfig
import com.example.dailypulse.data.articles.dto.ArticleDTO
import com.example.dailypulse.data.articles.dto.ArticlesResponseDTO
import com.example.dailypulse.utils.Endpoints
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.parameter
import io.ktor.client.request.request
import io.ktor.http.HttpMethod

class ArticlesRemoteDatasource(private val http: HttpClient) {


    suspend fun fetchArticles(): List<ArticleDTO> {
        val response =
            http.request(urlString = Endpoints.articles) {
                HttpMethod.Get
                parameter("q", "tesla")
                parameter("sortBy", "publishedAt")
                parameter("apiKey", EnvironmentConfig.apiKey)
            }
        return response.body<ArticlesResponseDTO>().articles
    }
}