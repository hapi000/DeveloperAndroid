

package com.example.android.trackr.ui.widget

import android.app.Application
import androidx.test.core.app.ApplicationProvider
import com.example.android.trackr.R
import com.example.android.trackr.TestApplication
import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(application = TestApplication::class)
class StarButtonTest {
    private val application: Application = ApplicationProvider.getApplicationContext()

    @Test
    fun when_starred() {
        val starButton = StarButton(application)
        starButton.isChecked = true
        assertThat(starButton.drawableResId).isEqualTo(R.drawable.ic_star)
        assertThat(starButton.contentDescription).isEqualTo(application.resources.getString(R.string.starred))
    }

    @Test
    fun when_unStarred() {
        val starButton = StarButton(application)
        starButton.isChecked = false
        assertThat(starButton.drawableResId).isEqualTo(R.drawable.ic_star_border)
        assertThat(starButton.contentDescription).isEqualTo(application.resources.getString(R.string.unstarred))
    }
}