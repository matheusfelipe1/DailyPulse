package com.example.dailypulse.di.sources

import com.example.dailypulse.data.source.datasources.SourcesLocalDatasource
import com.example.dailypulse.data.source.datasources.SourcesRemoteDatasource
import com.example.dailypulse.data.source.repository.SourceRepositoryImpl
import com.example.dailypulse.domain.source.repository.SourceRepository
import com.example.dailypulse.domain.source.usecases.GetSourcesUseCase
import com.example.dailypulse.presentation.sources.viewModels.SourceViewModel
import org.koin.dsl.module

val sourcesModule = module {
    single { SourcesRemoteDatasource(get()) }
    single { SourcesLocalDatasource(get()) }
    single<SourceRepository> { SourceRepositoryImpl(get(), get()) }
    single { GetSourcesUseCase(get()) }
    single { SourceViewModel(get()) }
}