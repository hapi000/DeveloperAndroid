

package com.example.android.trackr.compose.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun StarIconButton(
    onClick: () -> Unit,
    filled: Boolean,
    contentDescription: String?,
    modifier: Modifier = Modifier,
) {
    IconButton(
        onClick = onClick,
        modifier = modifier,
    ) {
        Image(
            imageVector = if (filled) Icons.Default.Star else Icons.Default.StarBorder,
            contentDescription = contentDescription,
            colorFilter = ColorFilter.tint(TrackrTheme.colors.star),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewStarIconButtons() {
    TrackrTheme {
        Column {
            StarIconButton(
                onClick = {},
                filled = true,
                contentDescription = null,
            )
            StarIconButton(
                onClick = {},
                filled = false,
                contentDescription = null,
            )
        }
    }
}
