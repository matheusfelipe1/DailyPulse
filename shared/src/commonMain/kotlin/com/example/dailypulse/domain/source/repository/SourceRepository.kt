package com.example.dailypulse.domain.source.repository

import com.example.dailypulse.domain.source.entity.SourceEntity
import com.example.dailypulse.utils.CustomResult

interface SourceRepository {
    suspend fun getSources(forceFetch: Boolean): CustomResult<List<SourceEntity>, Throwable>
}