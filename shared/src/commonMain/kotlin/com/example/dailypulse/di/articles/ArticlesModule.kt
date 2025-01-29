package com.example.dailypulse.di.articles

import com.example.dailypulse.data.articles.datasources.ArticlesRemoteDatasource
import com.example.dailypulse.domain.articles.usecases.GetArticlesUseCase
import com.example.dailypulse.data.articles.datasources.ArticlesLocalDatasource
import com.example.dailypulse.data.articles.repository.ArticlesRepositoryImpl
import com.example.dailypulse.domain.articles.repository.ArticlesRepository
import com.example.dailypulse.presentation.articles.viewmodels.ArticlesViewModel
import org.koin.dsl.module

val articlesModule = module {
    single { GetArticlesUseCase(get()) }
    single { ArticlesViewModel(get()) }
    single { ArticlesLocalDatasource(get()) }
    single { ArticlesRemoteDatasource(get()) }
    single<ArticlesRepository> { ArticlesRepositoryImpl(get(), get()) }
}