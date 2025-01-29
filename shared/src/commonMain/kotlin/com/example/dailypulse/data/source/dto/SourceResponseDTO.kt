package com.example.dailypulse.data.source.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SourceResponseDTO(
    @SerialName("status")
    val status: String,
    @SerialName("sources")
    val sources: List<SourceDTO>
)