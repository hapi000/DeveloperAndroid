

package com.example.android.trackr.ui.utils

import android.content.Context
import android.text.TextUtils
import com.example.android.trackr.R
import com.example.android.trackr.data.TaskSummary
import com.example.android.trackr.utils.DateTimeUtils
import java.time.Clock

object AccessibilityUtils {
    private const val COLON_SEPARATOR = ": "
    private const val PERIOD_SEPARATOR = "."
    private const val PERIOD_SEPARATOR_AND_SPACE = "$PERIOD_SEPARATOR "

    fun taskSummaryLabel(context: Context, taskSummary: TaskSummary, clock: Clock): String {
        val sb = StringBuffer()

        sb.append(taskSummary.title).append(PERIOD_SEPARATOR_AND_SPACE)

        sb.append(context.getString(R.string.owner)).append(COLON_SEPARATOR)
        sb.append(taskSummary.owner.username).append(PERIOD_SEPARATOR_AND_SPACE)

        sb.append(
            DateTimeUtils.durationMessageOrDueDate(
                context.resources,
                taskSummary.dueAt,
                clock
            )
        )
        sb.append(if (taskSummary.tags.isEmpty()) PERIOD_SEPARATOR else PERIOD_SEPARATOR_AND_SPACE)

        sb.append(
            TextUtils.join(
                PERIOD_SEPARATOR_AND_SPACE,
                taskSummary.tags.map { context.getString(R.string.tag_label, it.label) })
        )

        return sb.toString()
    }
}