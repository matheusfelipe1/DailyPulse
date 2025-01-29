package com.example.dailypulse.di

import com.example.dailypulse.di.articles.articlesModule
import com.example.dailypulse.di.sources.sourcesModule

val sharedKoinModule = listOf(
    articlesModule,
    networkModule,
    sourcesModule
)