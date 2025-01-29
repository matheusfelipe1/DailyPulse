package com.example.dailypulse.presentation.sources.state

import com.example.dailypulse.domain.source.entity.SourceEntity

data class SourceState(
    val loading: Boolean = false,
    val isRefreshing: Boolean = false,
    val errorMessage: String? = null,
    val sources: List<SourceEntity> = emptyList(),
)