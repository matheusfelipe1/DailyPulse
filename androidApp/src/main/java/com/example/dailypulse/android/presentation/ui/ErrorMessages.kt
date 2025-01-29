package com.example.dailypulse.android.presentation.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun ErrorMessages(message: String) {
    Text(text = message, color = Color.Red)
}