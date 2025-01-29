package com.example.dailypulse.di

import com.example.dailypulse.presentation.articles.viewmodels.ArticlesViewModel
import com.example.dailypulse.presentation.sources.viewModels.SourceViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin

fun initKoin() {
    val modules = sharedKoinModule + databaseModule

    startKoin {
        modules(modules)
    }
}

class ArticlesInjector : KoinComponent {
    val articlesViewModel: ArticlesViewModel by inject()
}

class SourcesInjector: KoinComponent {
    val sourcesViewModel: SourceViewModel by inject()
}