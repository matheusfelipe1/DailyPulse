package com.example.dailypulse.data.source.mapper

import com.example.dailypulse.data.source.dto.SourceDTO
import com.example.dailypulse.domain.source.entity.SourceEntity

fun mapperSources(sources: List<SourceDTO>): List<SourceEntity> =
    sources.map { source ->
        SourceEntity(
            id = source.id,
            name = source.name,
            desc = source.desc ?: "...",
            language = source.language,
            country = source.country
        )
}.toList()