

package com.example.android.trackr.ui.utils

import android.app.Application
import android.content.res.Resources
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.android.trackr.data.Avatar
import com.example.android.trackr.data.Tag
import com.example.android.trackr.data.TagColor
import com.example.android.trackr.data.TaskSummary
import com.example.android.trackr.data.TaskStatus
import com.example.android.trackr.data.User
import com.example.android.trackr.utils.DateTimeUtils
import io.mockk.every
import io.mockk.mockkObject
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.time.Clock
import java.time.Instant
import java.time.ZoneId
import com.google.common.truth.Truth.assertThat


@RunWith(AndroidJUnit4::class)
class AccessibilityUtilsTest {
    private val dateInEpochSecond = 1584310694L // March 15, 2020
    private val fakeClock =
        Clock.fixed(Instant.ofEpochSecond(dateInEpochSecond), ZoneId.of("US/Central"))
    private lateinit var resources: Resources
    private lateinit var application: Application

    @Before
    fun setup() {
        application = ApplicationProvider.getApplicationContext()
        resources = application.resources

        mockkObject(DateTimeUtils)
        every {
            DateTimeUtils.durationMessageOrDueDate(
                resources,
                any(),
                fakeClock
            )
        } returns dateTimeValue
    }

    @Test
    fun taskSummaryLabel_noTags() {
        assertThat(
            AccessibilityUtils.taskSummaryLabel(
                application,
                taskSummary,
                fakeClock
            )
        ).isEqualTo("task 1. Owner: user. $dateTimeValue.")
    }

    @Test
    fun taskSummaryLabel_withTags() {
        assertThat(
            AccessibilityUtils.taskSummaryLabel(
                application,
                taskSummaryWithTags,
                fakeClock
            )
        ).isEqualTo("task 2. Owner: user. $dateTimeValue. Tag: tag1. Tag: tag2")
    }


    companion object {
        private val user1 = User(1, "user", Avatar.DEFAULT_USER)
        private val tag1 = Tag(1, "tag1", TagColor.BLUE)
        private val tag2 = Tag(2, "tag2", TagColor.RED)
        private const val dateTimeValue = "Due today"

        var taskSummary = TaskSummary(
            id = 1,
            title = "task 1",
            dueAt = Instant.now(),
            owner = user1,
            status = TaskStatus.IN_PROGRESS,
            tags = emptyList(),
            orderInCategory = 1,
            starred = false,
        )

        var taskSummaryWithTags = TaskSummary(
            id = 2,
            title = "task 2",
            dueAt = Instant.now(),
            owner = user1,
            status = TaskStatus.IN_PROGRESS,
            tags = listOf(tag1, tag2),
            orderInCategory = 2,
            starred = false,
        )
    }
}