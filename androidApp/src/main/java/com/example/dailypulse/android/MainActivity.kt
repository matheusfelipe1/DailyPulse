package com.example.dailypulse.android

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import com.example.dailypulse.EnvironmentConfig
import com.example.dailypulse.android.presentation.AppScaffold

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val baseUrl = applicationContext.getString(R.string.BASE_URL)
        val apiKey = applicationContext.getString(R.string.API_KEY)

        EnvironmentConfig.initialize(baseUrl, apiKey)

        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppScaffold()
                }
            }
        }
    }
}

//@Preview
//@Composable
//fun DefaultPreview() {
//    MyApplicationTheme {
//        AboutScreen()
//    }
//}
