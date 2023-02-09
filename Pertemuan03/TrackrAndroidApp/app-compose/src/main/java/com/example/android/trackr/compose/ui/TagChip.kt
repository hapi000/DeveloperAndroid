

package com.example.android.trackr.compose.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.android.trackr.data.SeedData
import com.example.android.trackr.data.Tag

@Composable
fun TagChip(
    tag: Tag,
    modifier: Modifier = Modifier
) {
    Text(
        text = tag.label,
        color = TrackrTheme.colors.tagText(tag.color),
        modifier = modifier
            .background(
                color = TrackrTheme.colors.tagBackground(tag.color),
                shape = MaterialTheme.shapes.small
            )
            .padding(horizontal = 6.dp),
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewTagChip() {
    TrackrTheme {
        Column {
            for (tag in SeedData.Tags) {
                TagChip(tag = tag)
                Spacer(modifier = Modifier.height(4.dp))
            }
        }
    }
}
