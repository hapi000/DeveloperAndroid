

package com.example.android.trackr

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.accessibility.AccessibilityChecks
import org.junit.Before

@RunWith(AndroidJUnit4::class)
@LargeTest
class FabButtonTest {

    init {
        AccessibilityChecks.enable()
    }

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setup() {
        AccessibilityChecks.enable().setRunChecksFromRootView(true)
    }

    @Test
    fun fabButtonOpensTaskEditScreen() {
        onView(withId(R.id.add)).perform(ViewActions.click())
        onView(withId(R.id.fragment_task_edit)).check(matches(isDisplayed()))
    }
}