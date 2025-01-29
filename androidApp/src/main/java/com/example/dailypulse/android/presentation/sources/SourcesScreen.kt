package com.example.dailypulse.android.presentation.sources

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dailypulse.android.presentation.ui.ErrorMessages
import com.example.dailypulse.android.presentation.ui.Loader
import com.example.dailypulse.domain.source.entity.SourceEntity
import com.example.dailypulse.presentation.sources.viewModels.SourceViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshState
import org.koin.androidx.compose.getViewModel

@Composable
fun SourcesScreen(
    onBack: () -> Unit,
    viewModel: SourceViewModel = getViewModel()
) {
    var sourceState = viewModel.sourceState.collectAsState()
    var state = SwipeRefreshState(isRefreshing = sourceState.value.isRefreshing)

    SwipeRefresh(
        state = state,
        onRefresh = {
            viewModel.getSources(forceFetch = true)
        }
    ) {
        Column {
            AppBar(onBack)
            if (sourceState.value.loading)
                Loader()
            if (sourceState.value.errorMessage != null)
                ErrorMessages(sourceState.value.errorMessage!!)
            if (sourceState.value.sources.isNotEmpty())
                SourcesBody(sources = sourceState.value.sources)
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AppBar(onBack: () -> Unit) {
    TopAppBar(
        title = {
            Text(text = "Sources")
        },
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
            }
        }
    )
}

@Composable
private fun SourcesBody(
    sources: List<SourceEntity>
) {

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(sources) { source ->
            SourceRow(source)
        }
    }
}


@Composable
private fun SourceRow(
    source: SourceEntity
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = source.name,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp
            )
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = source.desc,
        )
        Spacer(modifier = Modifier.height(4.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = source.country,
                style = TextStyle(
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                "-",
                style = TextStyle(
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = source.language,
                style = TextStyle(
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            )
        }
    }
}