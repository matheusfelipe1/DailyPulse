package com.example.dailypulse.android.presentation.articles

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.dailypulse.android.presentation.ui.Loader
import com.example.dailypulse.android.presentation.ui.ErrorMessages
import com.example.dailypulse.android.utils.Screens
import com.example.dailypulse.domain.articles.entities.ArticleEntity
import com.example.dailypulse.presentation.articles.viewmodels.ArticlesViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshState
import org.koin.androidx.compose.getViewModel


@Composable
fun ArticlesScreen(
    redirectTo: (Screens) -> Unit,
    articlesViewModel: ArticlesViewModel = getViewModel(),
) {
    var articlesState = articlesViewModel.articlesState.collectAsState()
    var stateLoading = SwipeRefreshState(isRefreshing = articlesState.value.isRefreshing)
    Column {
        AppBar(redirectTo)
        if (articlesState.value.loading)
            Loader()
        if (articlesState.value.errorMessage != null)
            ErrorMessages(articlesState.value.errorMessage!!)
        if (articlesState.value.articles.isNotEmpty())
            SwipeRefresh(
                state = stateLoading,
                onRefresh = {
                    articlesViewModel.getArticles(true)
                }
            ) {
                ArticlesListView(articlesState.value.articles)
            }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AppBar(redirectTo: (Screens) -> Unit) {
    TopAppBar(
        title = {
            Text(text = "Articles")
        },
        actions = {
            IconButton(onClick = { redirectTo(Screens.SOURCES_SCREEN) }) {
                Icon(imageVector = Icons.Filled.List, contentDescription = "About Screen")
            }
            IconButton(onClick = { redirectTo(Screens.ABOUT_SCREEN) }) {
                Icon(imageVector = Icons.Default.Info, contentDescription = "About Screen")
            }

        }
    )
}

@Composable
private fun ArticlesListView(articles: List<ArticleEntity>) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(articles) { article -> ArticlesRow(article) }
    }
}

@Composable
private fun ArticlesRow(article: ArticleEntity) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        AsyncImage(
            model = article.imageUrl,
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = article.title,
            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 22.sp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = article.desc)
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = article.date,
            style = TextStyle(color = Color.Gray),
            modifier = Modifier.align(Alignment.End)
        )
        Spacer(modifier = Modifier.height(4.dp))
    }
}

