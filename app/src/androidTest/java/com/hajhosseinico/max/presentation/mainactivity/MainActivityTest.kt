package com.hajhosseinico.max.presentation.mainactivity

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import junit.framework.TestCase.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    // Initialize HiltAndroidRule
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun init() {
        // Inject Hilt dependencies
        hiltRule.inject()
    }

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun launchMainActivity() {
        activityRule.scenario.onActivity { activity ->
            // Check if activity is not null and successfully launched
            assertNotNull(activity)
        }
    }
}
