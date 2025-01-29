package com.example.dailypulse.domain.source.usecases

import com.example.dailypulse.domain.source.entity.SourceEntity
import com.example.dailypulse.domain.source.repository.SourceRepository
import com.example.dailypulse.utils.CustomResult

class GetSourcesUseCase(private val repository: SourceRepository) {

    suspend fun invoke(forceFetch: Boolean): CustomResult<List<SourceEntity>, Throwable> = repository.getSources(forceFetch)
}