

package com.example.android.trackr.compose.ui.detail

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.rememberInsetsPaddingValues

@Composable
fun TaskDetail(
    viewModel: TaskDetailViewModel
) {
    val padding = rememberInsetsPaddingValues(LocalWindowInsets.current.systemBars)
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
    ) {
        Text(text = "task ${viewModel.taskId}")
    }
}
