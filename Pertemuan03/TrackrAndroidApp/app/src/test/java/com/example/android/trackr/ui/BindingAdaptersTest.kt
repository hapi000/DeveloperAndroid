

package com.example.android.trackr.ui

import android.app.Application
import android.content.res.Resources
import android.view.View
import android.view.accessibility.AccessibilityNodeInfo
import android.widget.TextView
import androidx.test.core.app.ApplicationProvider
import com.example.android.trackr.TestApplication
import com.example.android.trackr.utils.DateTimeUtils
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockkObject
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.time.Clock
import java.time.Instant
import java.time.ZoneId

@RunWith(RobolectricTestRunner::class)
@Config(application = TestApplication::class)
class BindingAdaptersTest {

    private val dateInEpochSecond = 1584310694L // March 15, 2020
    private val fakeClock =
        Clock.fixed(Instant.ofEpochSecond(dateInEpochSecond), ZoneId.systemDefault())
    private lateinit var application: Application
    private lateinit var resources: Resources
    private lateinit var textView: TextView

    @Before
    fun setup() {
        application = ApplicationProvider.getApplicationContext()
        resources = application.resources
        textView = TextView(application)
    }

    @Test
    fun showFormattedDueMessageOrDueDate_withNullDueDate() {
        showFormattedDueMessageOrDueDate(textView, null, fakeClock)
        assertThat(textView.text).isEqualTo("")
    }

    @Test
    fun showFormattedDueMessageOrDueDate_withValue() {
        val value = "something"
        mockkObject(DateTimeUtils)
        every { DateTimeUtils.durationMessageOrDueDate(resources, any(), fakeClock) } returns value

        showFormattedDueMessageOrDueDate(textView, Instant.now(), fakeClock)

        assertThat(textView.text).isEqualTo(value)
    }

    @Test
    fun showFormattedDueMessageOrHide_withNullDueDate() {
        assertThat(textView.visibility).isEqualTo(View.VISIBLE)

        showFormattedDueMessageOrHide(textView, null, fakeClock)

        assertThat(textView.visibility).isEqualTo(View.GONE)
    }

    @Test
    fun showFormattedDueMessageOrHide_withEmptyMessage() {
        mockkObject(DateTimeUtils)
        every { DateTimeUtils.durationMessageOrDueDate(resources, any(), fakeClock) } returns ""

        assertThat(textView.visibility).isEqualTo(View.VISIBLE)

        showFormattedDueMessageOrHide(textView, Instant.now(), fakeClock)

        assertThat(textView.visibility).isEqualTo(View.GONE)
    }

    @Test
    fun showFormattedDueMessageOrHide_withNonEmptyMessage() {
        mockkObject(DateTimeUtils)
        every {
            DateTimeUtils.durationMessageOrDueDate(
                resources,
                any(),
                fakeClock
            )
        } returns "something"

        assertThat(textView.visibility).isEqualTo(View.VISIBLE)

        showFormattedDueMessageOrHide(textView, Instant.now(), fakeClock)

        assertThat(textView.visibility).isEqualTo(View.VISIBLE)
    }

    @Test
    fun addClickActionLabel_replacesActionLabel() {
        val view = View(application)
        val label = "something"

        addClickActionLabel(view, label)

        val nodeInfo = view.createAccessibilityNodeInfo()
        val actionClick = nodeInfo.actionList.filter {
            it.id == AccessibilityNodeInfo.ACTION_CLICK
        }[0]

        assertThat(actionClick.label).isEqualTo(label)
    }
}
