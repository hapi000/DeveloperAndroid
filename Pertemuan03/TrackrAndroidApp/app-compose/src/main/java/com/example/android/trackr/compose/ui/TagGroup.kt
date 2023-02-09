

package com.example.android.trackr.compose.ui

import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.android.trackr.compose.R
import com.example.android.trackr.data.SeedData
import com.example.android.trackr.data.Tag
import com.google.accompanist.flowlayout.FlowRow

@Composable
fun TagGroup(
    tags: List<Tag>,
    modifier: Modifier = Modifier,
    max: Int = Int.MAX_VALUE
) {
    FlowRow(
        modifier = modifier,
        crossAxisSpacing = 4.dp,
        mainAxisSpacing = 4.dp
    ) {
        for (tag in tags.take(max)) {
            TagChip(tag = tag)
        }
        if (tags.size > max) {
            Text(text = stringResource(R.string.more_tags, tags.size - max))
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun PreviewTagGroup() {
    TrackrTheme {
        TagGroup(
            tags = SeedData.Tags,
            modifier = Modifier.width(240.dp),
            max = 4,
        )
    }
}
