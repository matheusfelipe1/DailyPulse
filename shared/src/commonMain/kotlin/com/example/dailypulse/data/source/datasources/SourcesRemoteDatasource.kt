package com.example.dailypulse.data.source.datasources

import com.example.dailypulse.EnvironmentConfig
import com.example.dailypulse.data.source.dto.SourceDTO
import com.example.dailypulse.data.source.dto.SourceResponseDTO
import com.example.dailypulse.utils.Endpoints
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.parameter
import io.ktor.client.request.request
import io.ktor.http.HttpMethod

class SourcesRemoteDatasource(private val httpClient: HttpClient) {


    suspend fun getSources(): List<SourceDTO> {
        val response = httpClient.request(urlString = Endpoints.sources) {
            method = HttpMethod.Get
            parameter("apiKey", EnvironmentConfig.apiKey)
        }
        return response.body<SourceResponseDTO>().sources
    }
}