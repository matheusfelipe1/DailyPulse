package com.example.dailypulse.data.source.repository

import com.example.dailypulse.data.source.datasources.SourcesLocalDatasource
import com.example.dailypulse.data.source.datasources.SourcesRemoteDatasource
import com.example.dailypulse.data.source.dto.SourceDTO
import com.example.dailypulse.data.source.mapper.mapperSources
import com.example.dailypulse.domain.source.entity.SourceEntity
import com.example.dailypulse.domain.source.repository.SourceRepository
import com.example.dailypulse.utils.BaseRepository
import com.example.dailypulse.utils.CustomResult

class SourceRepositoryImpl(
    private val sourcesRemoteDatasource: SourcesRemoteDatasource,
    private val sourcesLocalDatasource: SourcesLocalDatasource,
): SourceRepository, BaseRepository() {

    override suspend fun getSources(forceFetch: Boolean): CustomResult<List<SourceEntity>, Throwable> {
        try {

            suspend fun fetchSources(): List<SourceEntity> {
                val fetchedSources = sourcesRemoteDatasource.getSources()
                sourcesLocalDatasource.insertAllSources(fetchedSources)
                return mapperSources(fetchedSources)
            }

            if (forceFetch) {
                sourcesLocalDatasource.clearAllSources()
                return handlerSuccess(fetchSources())
            }

            val sourcesFromDB = sourcesLocalDatasource.getAllSources()

            if (sourcesFromDB.isEmpty()) {
                return handlerSuccess(fetchSources())
            }

            return handlerSuccess(mapperSources(sourcesFromDB))

        } catch (e: Throwable) {
            println("error on get sources: $e")
            return handlerFailure(e)
        }
    }
}