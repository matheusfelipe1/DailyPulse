package com.example.dailypulse.data.source.datasources

import com.example.dailypulse.data.source.dto.SourceDTO
import com.example.dailypulse.db.DailyPulseDatabase

class SourcesLocalDatasource(private val database: DailyPulseDatabase) {

    fun getAllSources(): List<SourceDTO> =
        database.dailyPulseDatabaseQueries
            .selectAllSources(::mapSources).executeAsList()

    fun insertAllSources(sources: List<SourceDTO>) {
        database.dailyPulseDatabaseQueries.transaction {
            sources.forEach { source ->
                database.dailyPulseDatabaseQueries.insertSources(
                    source.id,
                    source.name,
                    source.desc,
                    source.language,
                    source.country,
                )
            }
        }
    }

    fun clearAllSources() = database.dailyPulseDatabaseQueries.removeAllSources()

    private fun mapSources(
        id: String,
        name: String,
        desc: String?,
        language: String,
        country: String,
    ): SourceDTO = SourceDTO(id, name, desc, language, country)
}