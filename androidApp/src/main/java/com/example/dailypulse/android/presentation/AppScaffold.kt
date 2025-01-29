package com.example.dailypulse.android.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dailypulse.android.utils.Screens
import com.example.dailypulse.android.presentation.about.AboutScreen
import com.example.dailypulse.android.presentation.articles.ArticlesScreen
import com.example.dailypulse.android.presentation.sources.SourcesScreen

@Composable
fun AppScaffold() {

    val navController = rememberNavController()
    
    Scaffold {
        AppNav(
            navController = navController,
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
        )
    }
}

@Composable
private fun AppNav(navController: NavHostController, modifier: Modifier) {
    NavHost(
        navController = navController,
        startDestination = Screens.ARTICLES.route,
        modifier = modifier
    ) {

        fun redirectTo(screen: Screens) {
            navController.navigate(screen.route)
        }

        composable(Screens.ARTICLES.route) {
            ArticlesScreen(
                redirectTo = { screen -> redirectTo(screen) }
            )
        }
        composable(Screens.ABOUT_SCREEN.route) {
            AboutScreen(
                onBack = {
                    navController.popBackStack()
                },
            )
        }
        composable(Screens.SOURCES_SCREEN.route) {
            SourcesScreen(
                onBack = {
                    navController.popBackStack()
                },
            )
        }
    }
}